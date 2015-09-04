package com.idoorSys.controller;

import com.idoorSys.model.SysUser;
import com.idoorSys.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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


	@ResponseBody
	@RequestMapping("/loginTimeOut")
	public Map<String, String> timeout() {
		Map<String, String> json = new HashMap<>();
		json.put("statusCode", "301");
		json.put("message", "time out!");
		return json;
	}
	
	@RequestMapping("/empty")
	public void blackhole() {}
}
