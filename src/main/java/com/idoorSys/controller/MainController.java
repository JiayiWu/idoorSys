package com.idoorSys.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.idoorSys.utils.ModifiableRoutingDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.SysUser;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.service.SysUserService;
import com.idoorSys.utils.RoutingDataSource;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
public class MainController {

	SysUserService sysUserService = (SysUserService) SpringContextsUtil
			.getBean("sysUserService");

	@RequestMapping("/")
	public String main(Map<String, Object> model, HttpSession httpSession) {
		SysUser sysUser = (SysUser) httpSession.getAttribute("sysUser");


//		ModifiableRoutingDataSource dataSource = ((ModifiableRoutingDataSource) SpringContextsUtil.getBean("myDataSource"));
//		dataSource.setDataSourceKey("a");
//		System.out.println("set to db: a");
//		ComboPooledDataSource a = (ComboPooledDataSource) SpringContextsUtil.getBean("switcha");
//		for (Object ds : dataSource.getTargetDataSources().values()) {
//			System.out.println(((ComboPooledDataSource) ds).getJdbcUrl());
//		}

		sysUserService.preAdd();

		if (sysUser != null) {
			model.put("sysUser", sysUser);
			return "index";
		} else {
			return "login";
		}
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/index")
	public String main(@RequestParam("account") String account,
			@RequestParam("password") String password,
			Map<String, Object> model, HttpSession httpSession) {
		SysUser sysUser = sysUserService.checkExits(account, password);
		if (sysUser != null) {
			httpSession.setAttribute("sysUser", sysUser);
			model.put("sysUser", sysUser);
			if (sysUser.getRole().equals("admin")) {
				return "index";
			} else {
				return "index_appliance";
			}
		} else {
			model.put("sysUser", null);
			return "login";
		}
	}
	
	@RequestMapping("/empty")
	public String blackhole() {
		return "empty";
	}

}
