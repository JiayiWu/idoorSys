package com.idoorSys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.idoorSys.model.UGroup;
import com.idoorSys.service.UGroupService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.service.ExcelPermitionImportService;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(PermissionUserController.PATH)
public class PermissionUserController implements IdoorController {

	PermissionUserService permissionUserService = (PermissionUserService) SpringContextsUtil
			.getBean("permissionUserService");
	UGroupService uGroupService = (UGroupService)SpringContextsUtil.getBean("uGroupService");

	public static final String PATH = "permissionUser/";

	@Override
	@RequestMapping(MAPPING_LIST)
	public String list(Map<String, Object> model) {
		// TODO Auto-generated method stub
		permissionUserService.preAdd();
		model.put("pusers", permissionUserService.getAll());
		return PATH + "list";
	}

	@Override
	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable long id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		Msg msg = permissionUserService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("cardNum") String cardNum,
			@RequestParam("stdNum") String stdNum,
			@RequestParam("phone") String phone,
			@RequestParam("group") String group) {
		// TODO Auto-generated method stub
		PermissionUser permissionUser = new PermissionUser(id, cardNum, name,
				type, stdNum);
		permissionUser.setPhone(phone);
		permissionUser.setGroup(group);
		boolean isNewGroup = true;
		for (UGroup g: uGroupService.getAll()) {
			if (g.getName().equals(group)) {
				isNewGroup = false;
			}
		}
		if (isNewGroup) {
			uGroupService.add(new UGroup(group));
		}
		Msg msg = permissionUserService.update(permissionUser);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("cardNum") String cardNum,
			@RequestParam("stdNum") String stdNum,
			@RequestParam("phone") String phone,
			@RequestParam("group") String group) {
		// TODO Auto-generated method stub
		PermissionUser permissionUser = new PermissionUser(cardNum, name, type,
				stdNum);
		permissionUser.setPhone(phone);
		permissionUser.setGroup(group);
		boolean isNewGroup = true;
		for (UGroup g: uGroupService.getAll()) {
			if (g.getName().equals(group)) {
				isNewGroup = false;
			}
		}
		if (isNewGroup) {
			uGroupService.add(new UGroup(group));
		}
		Msg msg = permissionUserService.add(permissionUser);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable long id, Map<String, Object> model) {
		model.put("puser", permissionUserService.getbyId(id));
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return PATH + "edit";
	}

	@Override
	@RequestMapping(MAPPIND_PAGE_ADD)
	public String pageAdd(Map<String, Object> model) {
		uGroupService.preAdd();
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return PATH + "add";
	}

	@RequestMapping(MAPPING_FIND_BY_EXAMPLE)
	public String findByExample(@RequestParam("name") String name,
			Map<String, Object> model) {
		PermissionUser puser = new PermissionUser();
		puser.setName(name);
		List<PermissionUser> pusers = (List<PermissionUser>) permissionUserService
				.findByExample(puser);
		model.put("pusers", pusers);
		return PATH + LIST_PAGE;
	}

	@RequestMapping(MAPPING_FIND_BY_EXAMPLE_JSON)
	@ResponseBody
	public Map<String, Object> findByExampleJson(
			@RequestParam("name") String name) {
		Map<String, Object> map = new HashedMap();
		PermissionUser puser = new PermissionUser();
		puser.setName(name);
		List<PermissionUser> pusers = (List<PermissionUser>) permissionUserService
				.findByExample(puser);
		map.put("pusers", pusers);
		return map;
	}

	@RequestMapping("getByGroup")
	@ResponseBody
	public Map<String, Object> getByGroup(@RequestParam("group") String group) {
		Map<String, Object> map = new HashedMap();
		List<PermissionUser> pusers = null;
		if (group.equals("all")) {
			pusers = (List<PermissionUser>) permissionUserService.getAll();
		} else {
			pusers = permissionUserService.getByGroup(group);
		}
		map.put("pusers", pusers);
		return map;
	}

	@RequestMapping("getCardNumOX")
	@ResponseBody
	public Map<String, Object> getCardNumOX(
			@RequestParam("cardNum") String cardNum) {
		Map<String, Object> map = new HashedMap();
		String cardNumOX = ExcelPermitionImportService.getConvertedID(cardNum);
		map.put("cardNumOX", cardNumOX);
		return map;
	}

	@RequestMapping("group")
	public String showGroup(Map<String, Object> model) {
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return PATH+"group";
	}
	@RequestMapping("deleteGroup")
	public String deleteGroup(HttpServletRequest request) {
		String[] groupsToDelete = request.getParameterValues("groupToDelete");
		boolean success = true;
		for (String group: groupsToDelete) {
			Msg msg = uGroupService.deleteByName(group);
			if (msg == Msg.FAIL) {
				success = false;
			}
		}
		return success? DONE_PAGE: FAIL_PAGE;
	}
}
