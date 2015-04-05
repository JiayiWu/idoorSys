package com.idoorSys;

import com.idoorSys.utils.LocalIpAddressService;

public class MainTest {

	public static void main(String args[]) throws Exception {
//		new ClassPathXmlApplicationContext("contextTest.xml");
		LocalIpAddressService las = new LocalIpAddressService();
		String ipAddress = las.ipAddressOf("0002411");
		String ip = ipAddress.split(":")[0];
		int port = Integer.parseInt(ipAddress.split(":")[1]);
		System.out.println(ip);
		System.out.println(port);
		
		las.setIpAddress("0002411", "192.168.206.187", 8888);
		
		ipAddress = las.ipAddressOf("0002411");
		ip = ipAddress.split(":")[0];
		port = Integer.parseInt(ipAddress.split(":")[1]);
		System.out.println(ip);
		System.out.println(port);
	}

}
