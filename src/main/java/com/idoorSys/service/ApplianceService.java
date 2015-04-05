package com.idoorSys.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.idoorSys.model.Device;
import com.idoorSys.utils.Msg;

public class ApplianceService {
	private Socket socket = null;
	private String cachedRoomNo = null;
	public Device getDevice(String roomNo) throws IOException {
		checkSocket(roomNo);
		
		PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pr.println("$#GetRoom:"+roomNo);
		pr.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String reply = br.readLine();
		Device device = json2Device(reply);
		return device;
	}

	/**
	 *  发送设置房间状态指令
	 * @param command
	 * @return
	 */
	public Msg send(String command) {
		// empty command
		if (!command.contains("|")) {
			return Msg.SUCCESS;
		}
		String roomNo = command.split("\\|")[0];
		System.out.println(roomNo);
		try {
			checkSocket(roomNo);
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pr.println("$#SetRoom:"+command);
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {}
			e.printStackTrace();
			return Msg.FAIL;
		}
		return Msg.SUCCESS;
	}
	
	public void disconnect() throws IOException {
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}
	
	/**
	 * 与控制服务器连接
	 * @param roomNo
	 * @return Socket
	 * @throws IOException
	 */
	private void checkSocket(String roomNo) throws IOException {
		if (!roomNo.equals(this.cachedRoomNo)) {
			System.out.println("旧的:"+cachedRoomNo);
			System.out.println("新的:"+roomNo);
			this.cachedRoomNo = roomNo;
			System.out.println("建立新连接");
			// TODO 从配置文件获取roomNo 对应的 ip地址
			String ip = "192.168.206.187";
//			String ip = "localhost";
			int port = 8888;
			socket = new Socket(ip, port);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String ack1 = br.readLine();
				if (ack1.startsWith("Please")) {
					System.out.println("======================================\nGet: "
							+ ack1); // test
					PrintWriter pr = new PrintWriter(new OutputStreamWriter(
							socket.getOutputStream()));
					pr.println("webapp");
					pr.flush();
					String ack2 = br.readLine();
					if (ack2.startsWith("Ok")) {
						System.out.println("==================================\nGet: "
								+ ack2); // test
						System.out.println("成功连接控制服务器\n");
					} else {
						throw new RuntimeException("控制服务器口令错误，请联系运维人员");
					}
				} else {
					throw new RuntimeException("控制服务器口令错误，请联系运维人员");
				}
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {}
				e.printStackTrace();
				throw new IOException("控制服务器连接失败，请检查设备");
			}
		}
	}
	
	/**
	 * 从控制服务器返回的json字符串转化为对应的device状态
	 * @param json
	 * @return
	 * @throws IOException
	 */
	private Device json2Device(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> stateMap = mapper.readValue(json, Map.class);
		String roomNo = stateMap.get("No");
		String frontDoorState = stateMap.get("R0");
		String backDoorState = stateMap.get("R1");
		Map<String, String> deskState = new HashMap<>();
		Map<String, String> lightState = new HashMap<>();
		for (String key: stateMap.keySet()) {
			if (key.startsWith("D")) {
				deskState.put(key.substring(1), stateMap.get(key).equals("n")? "on": "of");
			}
			if (key.startsWith("L")) {
				lightState.put(key.substring(1), stateMap.get(key).equals("n")? "on": "of");
			}
		}
		Device device = new Device();
		device.setRoomNo(roomNo);
		device.setFrontDoorState(frontDoorState == null ? null: frontDoorState.equals("n")? "on": "of");
		device.setBackDoorState(backDoorState == null ? null: backDoorState.equals("n")? "on": "of");
		device.setDeskState(mapper.writeValueAsString(deskState));
		device.setLightState(mapper.writeValueAsString(lightState));
		return device;
	}
}
