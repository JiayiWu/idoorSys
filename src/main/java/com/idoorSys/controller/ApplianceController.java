package com.idoorSys.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.service.ApplianceService;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(ApplianceController.PATH)
public class ApplianceController {
	public static final String PATH = "appliance/";
	
	@RequestMapping("list")
	public String list(Map<String, Object> model) {
//		ApplianceService applianceService = (ApplianceService)SpringContextsUtil.getBean("applianceServie");
//		applianceService.sayHello();
		return PATH+"list";
	}
	@RequestMapping("send")
	public String send(@RequestParam("doorNo")String doorNo, Map<String, Object> model) {
		System.out.println(model.size());
		model.put("doorNo", doorNo);
		System.out.println(doorNo);
		return PATH+"list";
	}
}