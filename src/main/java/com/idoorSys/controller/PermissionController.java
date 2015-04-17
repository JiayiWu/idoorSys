package com.idoorSys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.idoorSys.utils.ModifiableRoutingDataSource;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(PermissionController.PATH)
public class PermissionController implements IdoorController {

	PermissionService permissionService = (PermissionService) SpringContextsUtil
			.getBean("permissionService");
	RoomService roomService = (RoomService) SpringContextsUtil
			.getBean("roomService");
	PermissionUserService permissionUserService = (PermissionUserService) SpringContextsUtil
			.getBean("permissionUserService");

	public static final String PATH = "permission/";

	@Override
	@RequestMapping(MAPPING_LIST)
	public String list(Map<String, Object> model) {
		// permissionService.preAdd();
		List<Permission> permissions = (List<Permission>) permissionService
				.getAll();
		model.put("permissions",permissions);
		return PATH + LIST_PAGE;
	}

	@RequestMapping(MAPPING_FIND_BY_EXAMPLE)
	public String findByExample(@RequestParam("userName") String userName,
			@RequestParam("roomName") String roomName, Map<String, Object> model) {
		List<Permission> permissions = (List<Permission>) permissionService
				.findByCondition(userName, roomName);
		model.put("permissions", permissions);
		return PATH + LIST_PAGE;
	}
//
	@Override
//	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable long id, Map<String, Object> model) {
		Msg msg = permissionService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	@RequestMapping(MAPPING_DELETE)
	public void delete(@PathVariable long id, Map<String, Object> model, HttpServletResponse response) throws IOException {
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

	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") long id,
			@RequestParam("roomId") long roomId,
			@RequestParam("type") int type,
			@RequestParam("cardNum") String cardNum) {
		// permissionService.update(new Permission(id,roomId,type,cardNum));
		return DONE_PAGE;
	}

	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("roomId") long roomId,
			@RequestParam("cardNum") String cardNum,
			@RequestParam("type") String type, Map<String, Object> model) {
		Msg msg = permissionService.add(new Permission(new Room(roomId),
				new PermissionUser(cardNum), type));
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	@RequestMapping("multiAdd")
	public String multiAdd(@RequestParam("users[]") String[] users,
			@RequestParam("rooms[]") long[] rooms,
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
			return DONE_PAGE;
		} else {
			return FAIL_PAGE;
		}
	}

	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable("id") long id,
			Map<String, Object> model) {
		model.put("permission", permissionService.getbyId(id));
		return PATH + EDIT_PAGE;
	}

	@Override
	@RequestMapping("page/multiAdd")
	public String pageAdd(Map<String, Object> model) {
		List<Room> rooms = (List<Room>) roomService.getAll();
		// List<Long> ids=new ArrayList<Long>();
		List<PermissionUser> permissionUsers = (List<PermissionUser>) permissionUserService
				.getAll();
		// List<String> cardNums=new ArrayList<String>();

		// for(int i=0;i<rooms.size();i++) ids.add(rooms.get(i).getId());
		// for(int i=0;i<permissionUsers.size();i++)
		// cardNums.add(permissionUsers.get(i).getCardNum());
		model.put("rooms", rooms);
		model.put("pusers", permissionUsers);
		return PATH + "multiAdd";
	}
}
