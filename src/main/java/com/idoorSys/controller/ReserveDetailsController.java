package com.idoorSys.controller;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idoorSys.model.ReserveDetails;
import com.idoorSys.service.ReserveDetailsService;
import com.idoorSys.utils.SpringContextsUtil;

@Controller
@RequestMapping(ReserveDetailsController.PATH)
public class ReserveDetailsController implements IdoorController {

	ReserveDetailsService reserveDetailsService = (ReserveDetailsService) SpringContextsUtil
			.getBean("reserveDetailsService");

	public static final String PATH = "reserveDetail/";

	@Override
	@RequestMapping(MAPPING_LIST)
	public String list(Map<String, Object> model) {
		// TODO Auto-generated method stub
		reserveDetailsService.preAdd();
		model.put("reserveDetails", reserveDetailsService.getAll());
		return PATH + "list";
	}

	@Override
	@RequestMapping(MAPPING_DELETE)
	public String delete(@PathVariable long id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		reserveDetailsService.deleteById(id);
		return DONE_PAGE;
	}

	@RequestMapping(MAPPING_UPDATE)
	public String update(@RequestParam("id") int id,
			@RequestParam("userid") String userid,
			@RequestParam("roomNum") String roomNum,
			@RequestParam("inTime") String inTime,
			@RequestParam("outTime") String outTime,
			@RequestParam("doorNum") String doorNum,
			@RequestParam("deskNum") String deskNum,
			@RequestParam("deskLeftRight") String deskLeftRight) {
		// TODO Auto-generated method stub
		reserveDetailsService.update(new ReserveDetails(id, userid, roomNum,
				inTime, outTime, doorNum, deskNum, deskLeftRight));
		return DONE_PAGE;
	}

	@RequestMapping(MAPPING_ADD)
	public String add(@RequestParam("userid") String userid,
			@RequestParam("roomNum") String roomNum,
			@RequestParam("inTime") String inTime,
			@RequestParam("outTime") String outTime,
			@RequestParam("doorNum") String doorNum,
			@RequestParam("deskNum") String deskNum,
			@RequestParam("deskLeftRight") String deskLeftRight) {
		// TODO Auto-generated method stub
		reserveDetailsService.add(new ReserveDetails(userid, roomNum, inTime,
				outTime, doorNum, deskNum, deskLeftRight));
		return DONE_PAGE;
	}

	@Override
	@RequestMapping(MAPPING_PAGE_EDIT)
	public String pageEdit(@PathVariable long id, Map<String, Object> model) {
		// TODO Auto-generated method stub
		model.put("reserveDetail", reserveDetailsService.getbyId(id));
		return PATH + "edit";
	}

	@Override
	@RequestMapping(MAPPIND_PAGE_ADD)
	public String pageAdd(Map<String, Object> model) {
		// TODO Auto-generated method stub
		return PATH + "add";
	}

}
