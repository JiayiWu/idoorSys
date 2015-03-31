package com.idoorSys.controller;

import java.util.List;
import java.util.Map;

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
	public String send(@RequestParam("newState")Device newState) {
		Msg msg = applianceService.send("command");
		if (msg == Msg.SUCCESS) {
			return "ajaxDone";
		} else {
			return "ajaxFail";
		}
	}
}