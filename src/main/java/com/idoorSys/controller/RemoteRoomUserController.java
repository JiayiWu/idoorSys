package com.idoorSys.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.RemoteRoomUser;
import com.idoorSys.service.RemoteRoomUserService;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(RemoteRoomUserController.PATH)
public class RemoteRoomUserController implements IdoorController {

	RemoteRoomUserService remoteRoomUserService = (RemoteRoomUserService) SpringContextsUtil
			.getBean("remoteRoomUserService");

	public static final String PATH = "remoteRoomUser/";

	@Override
	@RequestMapping(MAPPING_LIST)
	public String list(Map<String, Object> model) {
		// TODO Auto-generated method stub
		remoteRoomUserService.preAdd();
		model.put("rusers", remoteRoomUserService.getAll());
		return PATH + "list";
	}

	@Override
	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable long id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		remoteRoomUserService.deleteById(id);
		return DONE_PAGE;
	}

	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") int id,
			@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("cardid") String cardid,
			@RequestParam("userIdentity") String userIdentity) {
		// TODO Auto-generated method stub
		remoteRoomUserService.update(new RemoteRoomUser(id, userid, username,
				cardid, userIdentity));
		return DONE_PAGE;
	}

	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("cardid") String cardid,
			@RequestParam("userIdentity") String userIdentity) {
		// TODO Auto-generated method stub
		remoteRoomUserService.add(new RemoteRoomUser(userid, username, cardid,
				userIdentity));
		return DONE_PAGE;
	}

	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable long id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		model.put("ruser", remoteRoomUserService.getbyId(id));
		return PATH + "edit";
	}

	@Override
	@RequestMapping(MAPPIND_PAGE_ADD)
	public String pageAdd(Map<String, Object> model) {
		// TODO Auto-generated method stub
		return PATH + "add";
	}

}
