package com.idoorSys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.utils.RoutingDataSource;


@Controller
@RequestMapping(DBController.PATH)
public class DBController {
	public static final String PATH = "dbmanage/";

	@RequestMapping("list")
	public String list(Map<String, Object> model) {
		List<String> dbList = new ArrayList<>();
		dbList.addAll(Arrays.asList("ds1", "ds3", "ds4"));
		model.put("dbList", dbList);
		return PATH+"switch";
	}
	
	@RequestMapping("switch")
	public String SwitchDB(@RequestParam("dabaSourceKey")String key){
		RoutingDataSource.setDataSourceKey(key);
		System.out.println("dataSourceKey is set to "+key);
		return "ajaxDone";
	}
}
