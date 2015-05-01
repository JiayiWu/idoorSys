package com.idoorSys.controller;

import com.idoorSys.model.SwipingRecord;
import com.idoorSys.service.SwipingService;
import com.idoorSys.utils.SpringContextsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Ezio on 4/17/2015.
 */
@Controller
@RequestMapping(SwipingController.PATH)
public class SwipingController {
    public static final String PATH = "swiping/";

    private SwipingService swipingService = (SwipingService) SpringContextsUtil.getBean("swipingService");

    @RequestMapping("list")
    public String listRecord(Map<String, Object> model) {
        List<SwipingRecord> records = ((List<SwipingRecord>) swipingService.getAll());
        model.put("swipingRecords", records);
        model.put("currentPage", "user");
        return PATH+"list";
    }

    @RequestMapping("listAnonymous")
    public String listAnonymous(Map<String, Object> model) {
        List<SwipingRecord> records = ((List<SwipingRecord>) swipingService.getAnonymousRecords());
        model.put("swipingRecords", records);
        model.put("currentPage", "anonymous");
        return PATH+"list";
    }
    @RequestMapping("findByExample")
    public String findByExample(@RequestParam("roomName")String roomName
                            , @RequestParam("userName")String userName
                            , @RequestParam("startTime")String startTime
                            , @RequestParam("endTime")String endTime
                            , @RequestParam("currentPage")String currentPage
                            , Map<String, Object> model) {
        List<SwipingRecord> records = currentPage.equals("anonymous") ?
                (List<SwipingRecord>) swipingService.getAnonymousByExample(roomName
                        , startTime == ""? null: Timestamp.valueOf(startTime+ " 00:00:00")
                        , endTime == ""? null: Timestamp.valueOf(endTime+ " 23:59:59")):
                (List<SwipingRecord>) swipingService.getByExample(roomName, userName
                        , startTime == ""? null: Timestamp.valueOf(startTime+ " 00:00:00")
                        , endTime == ""? null: Timestamp.valueOf(endTime+ " 23:59:59"));
        model.put("swipingRecords", records);
        model.put("currentPage", currentPage);
        return PATH+"list";
    }
}
