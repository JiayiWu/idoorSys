package com.idoorSys.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.idoorSys.model.DeviceState;
import org.codehaus.jackson.map.ObjectMapper;

import com.idoorSys.utils.LocalIpAddressService;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

/**
 * 向房间设备发送电源控制信号
 */
@Service
public class ApplianceService {
	public DeviceState getDevice(String roomNo) throws IOException, SQLException {
		Socket socket = getSocketTo(roomNo);
		
		PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pr.println("$#GetRoom:"+roomNo);
		pr.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String reply = br.readLine();
		System.out.println("reply is "+reply);
		
		disconnect(socket);
		
		DeviceState deviceState = json2Device(reply);
		return deviceState;
	}

	/**
	 *  发送设置房间状态指令
	 * @param command
	 * @return
	 */
	public Msg send(String command) {
		if (!command.contains("|")) {
			return Msg.SUCCESS;
		}
		String roomNo = command.split("\\|")[0];
		Socket socket = null;
		try {
			socket = getSocketTo(roomNo);
			
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("send $#SetRoom:"+command);
			pr.println("$#SetRoom:"+command);
			pr.flush();
//			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			System.out.println(br.readLine());
			
			disconnect(socket);
		} catch (IOException e) {
			e.printStackTrace();
			return Msg.FAIL;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Msg.FAIL;
		} finally {
			try {
				disconnect(socket);
			} catch (IOException e1) {}
		}
		return Msg.SUCCESS;
	}
	
	public void disconnect(Socket socket) throws IOException {
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
	 * @throws SQLException 
	 */
	private Socket getSocketTo(String roomNo) throws IOException {
		// TODO 从配置文件获取roomNo 对应的 ip地址
		LocalIpAddressService lias = new LocalIpAddressService();
		String ipAddress = lias.ipAddressOf(roomNo);
		if(ipAddress == null) {
			throw new RuntimeException("抱歉，您所选房间的IP未录入服务器");
		}
		String ip = ipAddress.split(":")[0];
		int port = Integer.parseInt(ipAddress.split(":")[1]);
		Socket socket = new Socket(ip, port);
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
					return socket;
				} else {
					throw new RuntimeException("控制服务器口令错误，请联系运维人员");
				}
			} else {
				throw new RuntimeException("控制服务器口令错误，请联系运维人员");
			}
		} catch (IOException e) {
			try {
				disconnect(socket);
			} catch (IOException e1) {}
			e.printStackTrace();
			throw new IOException("控制服务器连接失败，请检查设备");
		}
	}
	
	/**
	 * 从控制服务器返回的json字符串转化为对应的device状态
	 * @param json
	 * @return
	 * @throws IOException
	 */
	private DeviceState json2Device(String json) throws IOException {
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
		DeviceState deviceState = new DeviceState();
		deviceState.setRoomNo(roomNo);
		deviceState.setFrontDoorState(frontDoorState == null ? null: frontDoorState.equals("n")? "on": "of");
		deviceState.setBackDoorState(backDoorState == null ? null: backDoorState.equals("n")? "on": "of");
		deviceState.setDeskState(mapper.writeValueAsString(deskState));
		deviceState.setLightState(mapper.writeValueAsString(lightState));
		return deviceState;
	}
}
