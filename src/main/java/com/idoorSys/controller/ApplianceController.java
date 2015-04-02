package com.idoorSys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.Device;
import com.idoorSys.model.Room;
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
	
	@RequestMapping("list")
	public String list(Map<String, Object> model) {
		List<Room> rooms = (List<Room>) roomService.getAll();
		model.put("rooms", rooms);
		return PATH+"list";
	}
	@RequestMapping("listDevice")
	public String listDevice(@RequestParam("roomId")String roomId, Map<String, Object> model) {
		Device device = applianceService.getDevice(roomId);
		model.put("device", device);
		return PATH+"list";
	}
	@RequestMapping("send")
	public String send(HttpServletRequest request) {
		String roomNo = request.getParameter("roomNo");
		Device oldDevice = applianceService.getDevice(roomNo);
		Map<String, String> oldDeskState = oldDevice.generateDeskStateMap();
		Map<String, String> oldLightState = oldDevice.generateLightStateMap();
		
		StringBuilder command = new StringBuilder(roomNo);
		if(!request.getParameter("frontDoorState").equals(oldDevice.getFrontDoorState())) {
			command.append("|"+"R0"+request.getParameter("frontDoorState"));
		}
		if(!request.getParameter("backDoorState").equals(oldDevice.getBackDoorState())) {
			command.append("|"+"R1"+request.getParameter("backDoorState"));
		}
		for (String desk: oldDeskState.keySet()) {
			if(!request.getParameter("D"+desk).equals(oldDeskState.get(desk))) {
				command.append("|"+"D"+desk+request.getParameter("D"+desk));
			}
		}
		for (String light: oldLightState.keySet()) {
			if(!request.getParameter("L"+light).equals(oldLightState.get(light))) {
				command.append("|"+"L"+light+request.getParameter("L"+light));
			}
		}
		command.append("#");
		Msg msg = applianceService.send(command.toString());
		if (msg == Msg.SUCCESS) {
			return "ajaxDone";
		} else {
			return "ajaxFail";
		}
	}
}