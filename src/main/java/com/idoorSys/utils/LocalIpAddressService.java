package com.idoorSys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalIpAddressService {
	
	public LocalIpAddressService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String ipAddressOf(String roomNo) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ip","root", "1234qwer");
			statement = connection.createStatement();
			String sql = "SELECT address FROM ipaddress WHERE roomNo = '%s'".replace("%s", roomNo.substring(0,5));
			resultSet = statement.executeQuery(sql);
			String ipAddress = null;
			while(resultSet.next()) {
				ipAddress = resultSet.getString(1);
				System.out.println(roomNo+"'s IPAddress is "+ipAddress);
			}
			resultSet.close();
			statement.close();
			connection.close();
			return ipAddress;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("网站数据库故障");
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	public void setIpAddress(String roomNo, String ip, int port) {
		String[] bits = ip.split("\\.");
		if(bits.length != 4 || port<2000 || port > 9999) {
			throw new NumberFormatException("对不起，你输入的ip地址不符合要求");
		}
		for (String bit: bits) {
			int i = Integer.parseInt(bit);
			if (i<0 || i>255) {
				throw new NumberFormatException("对不起，你输入的ip地址不符合要求");
			}
		}
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ip","root", "1234qwer");
			statement = connection.createStatement();
			String sql = "UPDATE ipaddress SET address='%a' WHERE roomNo = '%r'".replace("%a", ip+":"+port).replace("%r", roomNo.substring(0,5));
			statement.executeUpdate(sql);

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("网站数据库故障");
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
}
