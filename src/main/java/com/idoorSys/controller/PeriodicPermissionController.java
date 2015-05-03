package com.idoorSys.controller;

import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.service.PeriodicPermissionService;
import com.idoorSys.service.PermissionUserService;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import com.idoorSys.utils.SpringContextsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Ezio on 5/2/2015.
 */
@Controller
@RequestMapping(PeriodicPermissionController.PATH)
public class PeriodicPermissionController implements IdoorController {

    PeriodicPermissionService periodicPermissionService = ((PeriodicPermissionService) SpringContextsUtil
            .getBean("periodicPermissionService"));
    RoomService roomService = (RoomService) SpringContextsUtil
            .getBean("roomService");
    PermissionUserService permissionUserService = (PermissionUserService) SpringContextsUtil
            .getBean("permissionUserService");
    public static final String PATH = "periodicPermission/";

    private static final List<String> week = Arrays.asList("星期一","星期二","星期三","星期四","星期五","星期六","星期日");

    @Override
    @RequestMapping(MAPPING_LIST)
    public String list(Map<String, Object> model) {
        List<PeriodicPermission> permissions = (List<PeriodicPermission>) periodicPermissionService
                .getAll();
        model.put("permissions",permissions);
        model.put("week", week);
        return PATH + LIST_PAGE;
    }

    @RequestMapping(MAPPING_FIND_BY_EXAMPLE)
    public String findByExample(@RequestParam("userName") String userName,
                                @RequestParam("roomName") String roomName,
                                @RequestParam("dayOfWeek") int dayOfWeek, Map<String, Object> model) {
        List<PeriodicPermission> permissions = (List<PeriodicPermission>) periodicPermissionService
                .findByCondition(userName, roomName, dayOfWeek);
        model.put("permissions", permissions);
        model.put("week", week);
        return PATH + LIST_PAGE;
    }

    @RequestMapping(MAPPING_DELETE)
    public String delete(@PathVariable long id, Map<String, Object> model) {
        Msg msg = periodicPermissionService.deleteById(id);
        if (msg == Msg.SUCCESS)
            return DONE_PAGE;
        else
            return FAIL_PAGE;
    }

    @RequestMapping(MAPPING_PAGE_EDIT)
    public String pageEdit(@PathVariable("id") long id, Map<String, Object> model) {
        model.put("permission", periodicPermissionService.getbyId(id));
        model.put("week", week);
        return PATH+EDIT_PAGE;
    }

    @RequestMapping(MAPPING_UPDATE)
    public String update(@RequestParam("id") long id,
                         @RequestParam("roomId") long roomId,
                         @RequestParam("cardNum") String cardNum,
                         @RequestParam("dayOfWeek") int dayOfWeek,
                         @RequestParam("beginTime") String beginTime,
                         @RequestParam("endTime") String endTime) {
        Room room = new Room();
        room.setId(roomId);
        PermissionUser user = new PermissionUser();
        user.setCardNum(cardNum);
        try {
            PeriodicPermission permission = new PeriodicPermission(id, room, user, dayOfWeek, Time.valueOf(beginTime), Time.valueOf(endTime));
            Msg msg = periodicPermissionService.update(permission);
            return msg==Msg.SUCCESS ? DONE_PAGE: FAIL_PAGE;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return FAIL_PAGE;
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
        return PATH + "multiAdd";
    }

    @RequestMapping("multiAdd")
    public String multiAdd(@RequestParam("users[]") String[] users,
                           @RequestParam("rooms[]") long[] rooms,
                           @RequestParam("dayOfWeek") int dayOfWeek,
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
            return result==Msg.SUCCESS ? DONE_PAGE: FAIL_PAGE;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return FAIL_PAGE;
        }
    }
}
