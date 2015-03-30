package com.idoorSys.controller;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idoorSys.service.ExcelPermitionImportService;
import com.idoorSys.service.PermissionService;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
public class ExcelController {
	
	@Autowired
	ServletContext servletContext;

	ExcelPermitionImportService excelPermitionImportService = (ExcelPermitionImportService) SpringContextsUtil
			.getBean("excelPermitionImportService");

	@RequestMapping("/import")
	public String main(Map<String, Object> model) {
		excelPermitionImportService.importExcel(servletContext.getRealPath("/WEB-INF/2.xls"));
		return "ajaxDone";
	}

}
