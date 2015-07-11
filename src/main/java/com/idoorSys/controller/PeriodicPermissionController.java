package com.idoorSys.controller;

import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.service.PeriodicPermissionService;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;
import org.joda.time.Period;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Ezio on 5/2/2015.
 */
@Controller
@RequestMapping("/periodicPermission")
public class PeriodicPermissionController implements IdoorController {

    @Resource
    private PeriodicPermissionService periodicPermissionService;
    @Resource
    private RoomService roomService;
    @Resource
    private PermissionUserService permissionUserService;

    private static final List<String> week = Arrays.asList("星期一","星期二","星期三","星期四","星期五","星期六","星期日");

    @Override
    @RequestMapping("/list")
    public String list(Map<String, Object> model) {
        List<PeriodicPermission> totalPermissions = periodicPermissionService.getAll();
        List<PeriodicPermission> permissions = totalPermissions.size()>20? totalPermissions.subList(0, 20): totalPermissions;
        int pageNumShown = totalPermissions.size()/20+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;

        model.put("permissions",permissions);
        model.put("week", week);
        model.put("totalCount", totalPermissions.size());
        model.put("numPerPage", 20);
        model.put("pageNumShown", pageNumShown);
        model.put("currentPage",1);
        return "/periodicPermission/list";
    }
    @RequestMapping("/pagedList")
    public String pagedList(HttpServletRequest request, Map<String, Object> model) {

        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        int currentPage = Integer.parseInt(request.getParameter("pageNum"));
        int pageNumShown = totalCount/numPerPage+1; pageNumShown = pageNumShown>10 ? 10: pageNumShown;
        List<PeriodicPermission> permissions = periodicPermissionService.getPageAll(numPerPage * (currentPage - 1), numPerPage);

        model.put("permissions", permissions);
        model.put("totalCount", totalCount);
        model.put("numPerPage", numPerPage);
        model.put("pageNumShown", pageNumShown);
        model.put("currentPage",currentPage);
        return "/periodicPermission/list";
    }

    @RequestMapping("/findByExample")
    public String findByExample(@RequestParam("userName") String userName,
                                @RequestParam("roomName") String roomName,
                                @RequestParam("dayOfWeek") int dayOfWeek, Map<String, Object> model) {
        List<PeriodicPermission> permissions = (List<PeriodicPermission>) periodicPermissionService
                .findByCondition(userName, roomName, dayOfWeek);
        model.put("permissions", permissions);
        model.put("week", week);
        return "/periodicPermission/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Map<String, Object> model) {
        Msg msg = periodicPermissionService.deleteById(id);
        if (msg == Msg.SUCCESS)
            return "/ajaxDone";
        else
            return "/ajaxFail";
    }

    @RequestMapping("/page/edit/{id}")
    public String pageEdit(@PathVariable("id") int id, Map<String, Object> model) {
        model.put("permission", periodicPermissionService.getbyId(id));
        model.put("week", week);
        return "/periodicPermission/edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("roomId") int roomId,
                         @RequestParam("cardNum") String cardNum,
                         @RequestParam("dayOfWeek") char dayOfWeek,
                         @RequestParam("beginTime") String beginTime,
                         @RequestParam("endTime") String endTime) {
        Room room = new Room();
        room.setId(roomId);
        PermissionUser user = new PermissionUser();
        user.setCard_num(cardNum);
        try {
            PeriodicPermission permission = new PeriodicPermission(id, room, user, dayOfWeek, Time.valueOf(beginTime), Time.valueOf(endTime));
            Msg msg = periodicPermissionService.update(permission);
            return msg==Msg.SUCCESS ? "/ajaxDone": "/ajaxFail";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "/ajaxFail";
        }
    }

    @RequestMapping("page/multiAdd")
    public String pageAdd(Map<String, Object> model) {
        List<Room> rooms = (List<Room>) roomService.getAll();
        List<PermissionUser> permissionUsers = (List<PermissionUser>) permissionUserService
                .getAll();
        model.put("rooms", rooms);
        model.put("pusers", permissionUsers);
        model.put("week", week);
        return "/periodicPermission/multiAdd";
    }

    @RequestMapping("multiAdd")
    public String multiAdd(@RequestParam("users[]") String[] users,
                           @RequestParam("rooms[]") int[] rooms,
                           @RequestParam("dayOfWeek") char dayOfWeek,
                           @RequestParam("beginTime") String beginTime,
                           @RequestParam("endTime") String endTime) {
        try {
            Msg result = Msg.SUCCESS;
            for (int i = 0; i < users.length; i++) {
                PermissionUser permissionUser = new PermissionUser(users[i]);
                for (int j = 0; j < rooms.length; j++) {
                    Msg addResult = periodicPermissionService.add(new PeriodicPermission(new Room(rooms[j]),
                            permissionUser, dayOfWeek, Time.valueOf(beginTime), Time.valueOf(endTime)));
                    if (addResult == Msg.FAIL) {
                        result = Msg.FAIL;
                    }
                }
            }
            return result==Msg.SUCCESS ? "/ajaxDone": "/ajaxFail";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "/ajaxFail";
        }
    }
}
