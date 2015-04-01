package com.idoorSys.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.idoorSys.model.Device;
import com.idoorSys.utils.Msg;

public class ApplianceService {
	public Device getDevice(String roomId) {
		ObjectMapper mapper = new ObjectMapper();
		Device d = new Device();
		d.setId(1234);
		d.setRoomNum("roomId");
		d.setFrontDoorState("on");
		d.setBackDoorState("on");
		Map<String, String> deskState = new HashMap<>();
		deskState.put("01l", "on");
		deskState.put("01r", "of");
		deskState.put("02l", "on");
		deskState.put("02r", "of");
		Map<String, String> lightState = new HashMap<>();
		lightState.put("03l", "on");
		lightState.put("03r", "of");
		lightState.put("04l", "on");
		lightState.put("04r", "of");
		try {
			d.setDeskState(mapper.writeValueAsString(deskState));
			d.setLightState(mapper.writeValueAsString(lightState));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}

	public Msg send(String command) {
		
		return Msg.SUCCESS;
	}
}
