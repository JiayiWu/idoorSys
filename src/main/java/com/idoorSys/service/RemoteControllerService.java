package com.idoorSys.service;

import com.idoorSys.devicecontroller.SocketClient;

public class RemoteControllerService {

	void startAuth() {
		SocketClient.getInstance().sendMsg("88888");
	}

	void sendCommand(String selectedRoom, String selectedDoor, String destLR,
			String doorSwith) {
		String command = selectedRoom + "|" + selectedDoor + destLR + doorSwith;
		final String backMsg = SocketClient.getInstance().sendMsg(command);
	}

	void startConnect(String ip, int port) {
		SocketClient.getInstance().connect(ip, port);
	}

	void disConnect() {

	}

}
