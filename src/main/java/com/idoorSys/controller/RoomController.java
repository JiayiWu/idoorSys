package com.idoorSys.controller;

import com.idoorSys.model.Room;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController implements IdoorController {
	@Resource
	private RoomService roomService;

	@Override
	@RequestMapping("/list")
	public String list(Map<String, Object> model) {

		List<Room> totalRooms = roomService.getAll();
		List<Room> rooms = totalRooms.size()>20? totalRooms.subList(0, 20): totalRooms;
		int pageNumShown = totalRooms.size()/20+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;

		model.put("rooms", rooms);
		model.put("totalCount", totalRooms.size());
		model.put("numPerPage", 20);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",1);
		return "/room/list";
	}
	@RequestMapping("/pagedList")
	public String pagedList(HttpServletRequest request, Map<String, Object> model) {
		int totalCount = Integer.parseInt(request.getParameter("totalCount"));
		int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		int pageNumShown = totalCount/numPerPage+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;
		List<Room> rooms = roomService.getPageAll(numPerPage*(currentPage-1), numPerPage);

		model.put("rooms", rooms);
		model.put("totalCount", totalCount);
		model.put("numPerPage", numPerPage);
		model.put("pageNumShown", pageNumShown);
		model.put("currentPage",currentPage);
		return "/room/list";
	}

	@RequestMapping("/findByExample")
	public String findByExample(@RequestParam("name") String name,
			Map<String, Object> model) {
		Room room = new Room();
		room.setName(name);
		List<Room> rooms = (List<Room>) roomService.findByExample(room);
		model.put("rooms", rooms);
		return "/room/list";
	}

	@RequestMapping("findByExampleJson")
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

	@Override
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Map<String, Object> model) {
		Msg msg = roomService.deleteById(id);
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@RequestMapping("/update")
	public String update(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("nameEn") String nameEn,
			@RequestParam("type") String type) {
		Room room = new Room(id, name, type);
		room.setNum(nameEn);
		Msg msg = roomService.update(room);
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("nameEn") String nameEn, Map<String, Object> model) {
		Room room = new Room(name, type);
		room.setNum(nameEn);
		Msg msg = roomService.add(room);
		if (msg == Msg.SUCCESS)
			return "/ajaxDone";
		else
			return "/ajaxFail";
	}

	@Override
	@RequestMapping("/page/edit/{id}")
	public String pageEdit(@PathVariable("id") int id,
			Map<String, Object> model) {
		model.put("room", roomService.getbyId(id));
		return "/room/edit";
	}

	@Override
	@RequestMapping("/page/add")
	public String pageAdd(Map<String, Object> model) {
		return "/room/add";
	}

}
