package com.idoorSys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.Device;
import com.idoorSys.service.ApplianceService;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(ApplianceController.PATH)
public class ApplianceController {
	public static final String PATH = "appliance/";
	
	ApplianceService applianceService = (ApplianceService) SpringContextsUtil
			.getBean("applianceService");
	RoomService roomService = (RoomService) SpringContextsUtil
			.getBean("roomService");
	

	// TODO 房间编号获取
	private List<String> buildings = Arrays.asList("00","01","02");
	private List<String> units = Arrays.asList("02","01");
	private List<String> floors = Arrays.asList("4","3","2","1");
	private List<String> rooms = Arrays.asList("11","10","09","08","07","06","05","04","03","02","01");
	
	@RequestMapping("door")
	public String door(Map<String, Object> model) {
		model.put("buildings", buildings);
		model.put("units", units);
		model.put("floors", floors);
		model.put("rooms", rooms);
		return PATH+"door";
	}
	
	@RequestMapping("device")
	public String device(Map<String, Object> model) {
		model.put("buildings", buildings);
		model.put("units", units);
		model.put("floors", floors);
		model.put("rooms", rooms);
		return PATH+"device";
	}

	@RequestMapping("listDevice")
	public String listDevice(HttpServletRequest request, Map<String, Object> model) {
		model.put("buildings", buildings);
		model.put("units", units);
		model.put("floors", floors);
		model.put("rooms", rooms);
		System.err.println(request.getParameter("fromPage"));
		String roomId = request.getParameter("building")
				+request.getParameter("unit")
				+request.getParameter("floor")
				+request.getParameter("room");
		Device device;
		try {
			device = applianceService.getDevice(roomId);
			model.put("device", device);
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		
		String fromPage = request.getParameter("fromPage");
		return fromPage.equals("door") ? PATH+"door": PATH+"device";
	}
	@RequestMapping("send")
	public String send(HttpServletRequest request) {
		String roomNo = request.getParameter("roomNo");
		Device oldDevice;
		try {
			oldDevice = applianceService.getDevice(roomNo);
			Map<String, String> oldDeskState = oldDevice.generateDeskStateMap();
			Map<String, String> oldLightState = oldDevice.generateLightStateMap();
			
			StringBuilder command = new StringBuilder(roomNo);
			if(oldDevice.getFrontDoorState()!=null && !request.getParameter("frontDoorState").equals(oldDevice.getFrontDoorState())) {
				command.append("|"+"R0"+request.getParameter("frontDoorState"));
			}
			if(oldDevice.getBackDoorState() != null &&!request.getParameter("backDoorState").equals(oldDevice.getBackDoorState())) {
				command.append("|"+"R1"+request.getParameter("backDoorState"));
			}
			for (String desk: oldDeskState.keySet()) {
				if(!request.getParameter("D"+desk).equals(oldDeskState.get(desk))) {
					command.append("|"+"D"+desk+"l"+request.getParameter("D"+desk));
				}
			}
			for (String light: oldLightState.keySet()) {
				if(!request.getParameter("L"+light).equals(oldLightState.get(light))) {
					command.append("|"+"L"+light+"l"+request.getParameter("L"+light));
				}
			}
			command.append("#");
			try {
				Msg msg = applianceService.send(command.toString());
				if (msg == Msg.SUCCESS) {
					return "ajaxDoneForAppliance";
				} else {
					return "ajaxFailForAppliance";
				}
			} catch(RuntimeException e) {
				return "ajaxFailForAppliance";
			}
		} catch (IOException|SQLException e) {
			e.printStackTrace();
			return "ajaxFailForAppliance";
		}
	}
}