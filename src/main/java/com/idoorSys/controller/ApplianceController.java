package com.idoorSys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idoorSys.model.DeviceState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idoorSys.service.ApplianceService;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(ApplianceController.PATH)
public class ApplianceController {
	public static final String PATH = "appliance/";

	@Resource
	private ApplianceService applianceService;
	@Resource
	private RoomService roomService;

	String ajaxDone = "{\n" +
			"\t\"statusCode\":\"200\",\n" +
			"\t\"message\":\"success\",\n" +
			"\t\"navTabId\":\"\",\n" +
			"\t\"rel\":\"\",\n" +
			"\t\"callbackType\":\"forward\",\n" +
			"\t\"forwardUrl\":\"appliance/%fromPage%\",\n" +
			"\t\"confirmMsg\":\"\"\n" +
			"}";
	String ajaxFail = "{\n" +
			"\t\"statusCode\":\"300\",\n" +
			"\t\"message\":\"fail\",\n" +
			"\t\"navTabId\":\"\",\n" +
			"\t\"rel\":\"\",\n" +
			"\t\"callbackType\":\"forward\",\n" +
			"\t\"forwardUrl\":\"appliance/%fromPage%\",\n" +
			"\t\"confirmMsg\":\"\"\n" +
			"}\n";

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
		DeviceState deviceState;
		try {
			deviceState = applianceService.getDevice(roomId);
			model.put("deviceState", deviceState);
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		
		String fromPage = request.getParameter("fromPage");
		return fromPage.equals("door") ? PATH+"door": PATH+"deviceState";
	}
	@RequestMapping("send")
	public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String roomNo = request.getParameter("roomNo");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		DeviceState oldDeviceState;
		try {
			oldDeviceState = applianceService.getDevice(roomNo);
			Map<String, String> oldDeskState = oldDeviceState.generateDeskStateMap();
			Map<String, String> oldLightState = oldDeviceState.generateLightStateMap();
			
			StringBuilder command = new StringBuilder(roomNo);
			if(request.getParameter("frontDoorState")!=null
					&& oldDeviceState.getFrontDoorState()!=null
					&& !request.getParameter("frontDoorState").equals(oldDeviceState.getFrontDoorState())) {
				command.append("|"+"R0"+request.getParameter("frontDoorState"));
			}
			if(request.getParameter("backDoorState")!=null
					&& oldDeviceState.getBackDoorState() != null
					&&!request.getParameter("backDoorState").equals(oldDeviceState.getBackDoorState())) {
				command.append("|"+"R1"+request.getParameter("backDoorState"));
			}
			for (String desk: oldDeskState.keySet()) {
				if (request.getParameter("D" + desk) == null) {
					break;
				}
				if (!request.getParameter("D" + desk).equals(oldDeskState.get(desk))) {
					command.append("|" + "D" + desk + "l" + request.getParameter("D" + desk));
				}
			}
			for (String light: oldLightState.keySet()) {
				if (request.getParameter("D" + light) == null) {
					break;
				}
				if(!request.getParameter("L"+light).equals(oldLightState.get(light))) {
					command.append("|"+"L"+light+"l"+request.getParameter("L"+light));
				}
			}
			command.append("#");
			try {
				Msg msg = applianceService.send(command.toString());
				if (msg == Msg.SUCCESS) {
					System.out.println("success");
					out.print(ajaxDone.replace("%fromPage%",request.getParameter("fromPage")));
				} else {
					out.print(ajaxFail.replace("%fromPage%",request.getParameter("fromPage")));
				}
			} catch(RuntimeException e) {
				out.print(ajaxFail.replace("%fromPage%",request.getParameter("fromPage")));
			}
		} catch (IOException|SQLException e) {
			e.printStackTrace();
			out.print(ajaxFail.replace("%fromPage%",request.getParameter("fromPage")));
		}
	}
}