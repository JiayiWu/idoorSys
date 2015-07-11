package com.idoorSys.controller;

import java.util.List;
import java.util.Map;

import com.idoorSys.model.Permission;
import com.idoorSys.model.UGroup;
import com.idoorSys.service.ExcelPermitionImportService;
import com.idoorSys.service.UGroupService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idoorSys.model.PermissionUser;
//import com.idoorSys.service.ExcelPermitionImportService;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/permissionUser")
public class PermissionUserController implements IdoorController {
	@Resource
	private PermissionUserService permissionUserService;
	@Resource
	private UGroupService uGroupService;

	@Override
	@RequestMapping("/list")
	public String list(Map<String, Object> model) {
		List<PermissionUser> totalUsers = permissionUserService.getAll();
		List<PermissionUser> users = totalUsers.size()>20? totalUsers.subList(0, 20): totalUsers;
		int pageNumShown = totalUsers.size()/20+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;

		model.put("pusers", users);
		model.put("totalCount", totalUsers.size());
		model.put("numPerPage", 20);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",1);
		return "/permissionUser/list";
	}
	@RequestMapping("/pagedList")
	public String pagedList(HttpServletRequest request, Map<String, Object> model) {
		int totalCount = Integer.parseInt(request.getParameter("totalCount"));
		int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		int pageNumShown = totalCount/numPerPage+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;
		List<PermissionUser> users = permissionUserService.getPageAll(numPerPage * (currentPage - 1), numPerPage);

		model.put("pusers", users);
		model.put("totalCount", totalCount);
		model.put("numPerPage", numPerPage);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",currentPage);
		return "/permissionUser/list";
	}

	@Override
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		Msg msg = permissionUserService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") int id,
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
			return "/ajaxDone";
		else
			return "/ajaxFail";
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
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable int id, Map<String, Object> model) {
		model.put("puser", permissionUserService.getById(id));
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return "/permissionUser/edit";
	}

	@Override
	@RequestMapping(MAPPIND_PAGE_ADD)
	public String pageAdd(Map<String, Object> model) {
		uGroupService.preAdd();
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return "/permissionUser/add";
	}

	@RequestMapping("/findByExample")
	public String findByExample(@RequestParam("name") String name,
			Map<String, Object> model) {
		PermissionUser puser = new PermissionUser();
		puser.setName(name);
		List<PermissionUser> pusers = (List<PermissionUser>) permissionUserService
				.findByExample(puser);
		model.put("pusers", pusers);
		return "/permissionUser/list";
	}

	@RequestMapping("/findByExampleJson")
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

	@RequestMapping("/getByGroup")
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

	@RequestMapping("/getCardNumOX")
	@ResponseBody
	public Map<String, Object> getCardNumOX(
			@RequestParam("cardNum") String cardNum) {
		Map<String, Object> map = new HashedMap();
		String cardNumOX = ExcelPermitionImportService.getConvertedID(cardNum);
		map.put("cardNumOX", cardNumOX);
		return map;
	}

	@RequestMapping("/group")
	public String showGroup(Map<String, Object> model) {
		List<UGroup> groups = uGroupService.getAll();
		model.put("groups", groups);
		return "/permissionUser/group";
	}
	@RequestMapping("/deleteGroup")
	public String deleteGroup(HttpServletRequest request) {
		String[] groupsToDelete = request.getParameterValues("groupToDelete");
		boolean success = true;
		for (String group: groupsToDelete) {
			Msg msg = uGroupService.deleteByName(group);
			if (msg == Msg.FAIL) {
				success = false;
			}
		}
		return success? "/ajaxDone": "/ajaxFail";
	}
}
