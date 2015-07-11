package com.idoorSys.controller;

import com.idoorSys.model.SysUser;
import com.idoorSys.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainController {
	@Resource
	private SysUserService sysUserService;

	@RequestMapping("/")
	public String main(Map<String, Object> model, HttpSession httpSession) {

		SysUser sysUser = (SysUser) httpSession.getAttribute("sysUser");

		// 添加预先定义的登陆用户
		sysUserService.preAdd();
		if (sysUser != null) {
			model.put("sysUser", sysUser);
			return "/index";
		} else {
			return "/login";
		}
	}

	@RequestMapping("/login")
	public void login() {}
	@RequestMapping("/index")
	public String main(@RequestParam("account") String account,
			@RequestParam("password") String password,
			Map<String, Object> model, HttpSession httpSession) {

		SysUser sysUser = sysUserService.checkExits(account, password);
		if (sysUser != null) {
			httpSession.setAttribute("sysUser", sysUser);
			model.put("sysUser", sysUser);
			if (sysUser.getRole().equals("admin")) {
				return "/index";
			} else {
				return "/index_appliance";
			}
		} else {
			model.put("sysUser", null);
			return "/login";
		}
	}
	
	@RequestMapping("/empty")
	public void blackhole() {}
}
