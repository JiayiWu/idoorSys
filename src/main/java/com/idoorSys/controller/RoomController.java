package com.idoorSys.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idoorSys.model.Room;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(RoomController.PATH)
public class RoomController implements IdoorController {
	RoomService roomService = (RoomService) SpringContextsUtil
			.getBean("roomService");

	public static final String PATH = "room/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#list(java.util.Map)
	 */
	@Override
	@RequestMapping(MAPPING_LIST)
	public String list(Map<String, Object> model) {
		// roomService.preAdd();
		List<Room> rooms = (List<Room>) roomService.getAll();
		model.put("rooms", rooms);
		return PATH + LIST_PAGE;
	}

	@RequestMapping(MAPPING_FIND_BY_EXAMPLE)
	public String findByExample(@RequestParam("name") String name,
			Map<String, Object> model) {
		Room room = new Room();
		room.setName(name);
		List<Room> rooms = (List<Room>) roomService.findByExample(room);
		model.put("rooms", rooms);
		return PATH + LIST_PAGE;
	}

	@RequestMapping(MAPPING_FIND_BY_EXAMPLE_JSON)
	@ResponseBody
	public Map<String, Object> findByExampleJson(
			@RequestParam("name") String name) {
		Map<String, Object> map = new HashedMap();
		Room room = new Room();
		room.setName(name);
		List<Room> rooms = (List<Room>) roomService.findByExample(room);
		map.put("rooms", rooms);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#delete(long, java.util.Map)
	 */
	@Override
	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable long id, Map<String, Object> model) {
		Msg msg = roomService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#update(long,
	 * java.lang.String, java.lang.String)
	 */
	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("nameEn") String nameEn,
			@RequestParam("type") String type) {
		Room room = new Room(id, name, type);
		room.setNameEn(nameEn);
		Msg msg = roomService.update(room);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#add(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("nameEn") String nameEn, Map<String, Object> model) {
		Room room = new Room(name, type);
		room.setNameEn(nameEn);
		Msg msg = roomService.add(room);
		if (msg == Msg.SUCCESS)
			return DONE_PAGE;
		else
			return FAIL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#pageEdit(long,
	 * java.util.Map)
	 */
	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable("id") long id,
			Map<String, Object> model) {
		model.put("room", roomService.getbyId(id));
		return PATH + EDIT_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.controller.IdoorController#pageAdd(java.util.Map)
	 */
	@Override
	@RequestMapping(MAPPIND_PAGE_ADD)
	public String pageAdd(Map<String, Object> model) {
		return PATH + ADD_PAGE;
	}

}
