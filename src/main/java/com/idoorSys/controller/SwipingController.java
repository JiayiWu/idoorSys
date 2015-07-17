package com.idoorSys.controller;

import com.idoorSys.model.SwipingRecord;
import com.idoorSys.service.SwipingService;
import com.idoorSys.utils.SpringContextsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private SwipingService swipingService;

    @RequestMapping("list")
    public String listRecord(Map<String, Object> model) {
        List<SwipingRecord> totalRecords = swipingService.getAll();
        List<SwipingRecord> records = totalRecords.size() > 20 ? totalRecords.subList(0, 20) : totalRecords;
        int pageNumShown = totalRecords.size()/20+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;

        model.put("swipingRecords", records);
        model.put("currentPage", "user");
        model.put("totalCount", totalRecords.size());
        model.put("numPerPage", 20);
        model.put("pageNumShown", pageNumShown);
        model.put("currentPage",1);
        return PATH+"list";
    }

    @RequestMapping("listAnonymous")
    public String listAnonymous(Map<String, Object> model) {
        List<SwipingRecord> records = ((List<SwipingRecord>) swipingService.getAnonymousRecords());
        model.put("swipingRecords", records);
        model.put("currentPage", "anonymous");
        return PATH+"list";
    }

    @RequestMapping("pagedList")
    public String pagedList(HttpServletRequest request, Map<String, Object> model) {
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        int currentPage = Integer.parseInt(request.getParameter("pageNum"));
        int pageNumShown = totalCount/numPerPage+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;
        String isAnonymous = request.getParameter("currentPage");
        System.out.println("isAnonymous: "+ isAnonymous);
        List<SwipingRecord> records = swipingService.getPageAll(numPerPage * (currentPage - 1), numPerPage);

        model.put("swipingRecords", records);
        model.put("totalCount", totalCount);
        model.put("numPerPage", numPerPage);
        model.put("pageNumShown", pageNumShown);
        model.put("currentPage",currentPage);
        return PATH + "list";
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
