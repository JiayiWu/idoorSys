package com.idoorSys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.idoorSys.service.PeriodicPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.service.PermissionService;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/permission")
public class PermissionController implements IdoorController {

	@Resource
	private PermissionService permissionService;
	@Resource
	private RoomService roomService;
	@Resource
	private PermissionUserService permissionUserService;

	@Override
	@RequestMapping("/list")
	public String list(Map<String, Object> model) {
		// permissionService.preAdd();

		List<Permission> totalPermissions = permissionService.getAll();
		List<Permission> permissions = totalPermissions.size()>20? totalPermissions.subList(0, 20): totalPermissions;
		int pageNumShown = totalPermissions.size()/20+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;

		model.put("permissions",permissions);
		model.put("totalCount", totalPermissions.size());
		model.put("numPerPage", 20);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",1);
		return "/permission/list";
	}
	@RequestMapping("/pagedList")
	public String pagedList(HttpServletRequest request, Map<String, Object> model) {
		int totalCount = Integer.parseInt(request.getParameter("totalCount"));
		int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		int pageNumShown = totalCount/numPerPage+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;
		List<Permission> permissions = permissionService.getPageAll(numPerPage * (currentPage - 1), numPerPage);

		model.put("permissions", permissions);
		model.put("totalCount", totalCount);
		model.put("numPerPage", numPerPage);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",currentPage);
		return "/permission/list";
	}

	@RequestMapping("/findByExample")
	public String findByExample(@RequestParam("userName") String userName,
			@RequestParam("roomName") String roomName, Map<String, Object> model) {
		List<Permission> permissions = (List<Permission>) permissionService
				.findByCondition(userName, roomName);
		model.put("permissions", permissions);
		return "/permission/list";
	}
//
	@Override
//	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable int id, Map<String, Object> model) {
		Msg msg = permissionService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id, Map<String, Object> model, HttpServletResponse response) throws IOException {
		Msg msg = permissionService.deleteById(id);
		if (msg == Msg.SUCCESS)
			response.getWriter().print("{\n" +
					"\t\"statusCode\":\"200\",\n" +
					"\t\"message\":\"success\",\n" +
					"\t\"navTabId\":\"\",\n" +
					"\t\"rel\":\"\",\n" +
					"\t\"callbackType\":\"forward\",\n" +
					"\t\"forwardUrl\":\"permission/list\",\n" +
					"\t\"confirmMsg\":\"\"\n" +
					"}");
		else
			response.getWriter().print("{\n" +
					"\t\"statusCode\":\"300\",\n" +
					"\t\"message\":\"fail\",\n" +
					"\t\"navTabId\":\"\",\n" +
					"\t\"rel\":\"\",\n" +
					"\t\"callbackType\":\"forward\",\n" +
					"\t\"forwardUrl\":\"permission/list\",\n" +
					"\t\"confirmMsg\":\"\"\n" +
					"}");
	}

	@RequestMapping("/update")
	public String update(@RequestParam("id") int id,
			@RequestParam("roomId") int roomId,
			@RequestParam("type") String type,
			@RequestParam("cardNum") String cardNum) {
		Room room = new Room();
		room.setId(roomId);
		PermissionUser user = new PermissionUser();
		user.setCard_num(cardNum);
		permissionService.update(new Permission(id,room,user,type));
		return "/ajaxDone";
	}

	@RequestMapping("/add")
	public String add(@RequestParam("roomId") int roomId,
			@RequestParam("cardNum") String cardNum,
			@RequestParam("type") String type, Map<String, Object> model) {
		Msg msg = permissionService.add(new Permission(
				new Room(roomId),
				new PermissionUser(cardNum),
				type
		));
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@RequestMapping("/multiAdd")
	public String multiAdd(@RequestParam("users[]") String[] users,
			@RequestParam("rooms[]") int[] rooms,
			@RequestParam("type") String type) {
		Msg result = Msg.SUCCESS;
		for (int i = 0; i < users.length; i++) {
			PermissionUser permissionUser = new PermissionUser(users[i]);
			for (int j = 0; j < rooms.length; j++) {
				Msg addResult = permissionService.add(new Permission(new Room(rooms[j]),
						permissionUser, type));
				if (addResult == Msg.FAIL) {
					result = Msg.FAIL;
				}
			}
		}
		if (result == Msg.SUCCESS) {
			return "/ajaxDone";
		} else {
			return "/ajaxFail";
		}
	}

	@Override
	@RequestMapping("/page/edit/{id}")
	public String pageEdit(@PathVariable("id") int id,
			Map<String, Object> model) {
		model.put("permission", permissionService.getbyId(id));
		return "/permission/edit";
	}

	@Override
	@RequestMapping("page/multiAdd")
	public String pageAdd(Map<String, Object> model) {
		List<Room> rooms = roomService.getAll();
		List<PermissionUser> permissionUsers = permissionUserService.getAll();
		model.put("rooms", rooms);
		model.put("pusers", permissionUsers);
		return "/permission/multiAdd";
	}
}
