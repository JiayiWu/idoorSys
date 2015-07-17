package com.idoorSys.controller;

import com.idoorSys.model.Room;
import com.idoorSys.service.RoomService;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @RequestMapping("/list")
    public String list(Model model) {

        List<Room> totalRooms = roomService.getAll();
        List<Room> rooms = totalRooms.size() > 20 ? totalRooms.subList(0, 20) : totalRooms;
        int pageNumShown = totalRooms.size() / 20 + 1;
        pageNumShown = pageNumShown > 10 ? 10 : pageNumShown;

        model.addAttribute("rooms", rooms);
        model.addAttribute("totalCount", totalRooms.size());
        model.addAttribute("numPerPage", 20);
        model.addAttribute("pageNumShown", pageNumShown);
        model.addAttribute("currentPage", 1);
        return "/room/list";
    }

    @RequestMapping("/pagedList")
    public String pagedList(HttpServletRequest request, Model model) {
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
        int currentPage = Integer.parseInt(request.getParameter("pageNum"));
        int pageNumShown = totalCount / numPerPage + 1;
        pageNumShown = pageNumShown > 10 ? 10 : pageNumShown;
        List<Room> rooms = roomService.getPageAll(numPerPage * (currentPage - 1), numPerPage);

        model.addAttribute("rooms", rooms);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("numPerPage", numPerPage);
        model.addAttribute("pageNumShown", pageNumShown);
        model.addAttribute("currentPage", currentPage);
        return "/room/list";
    }

    @RequestMapping("/findByExample")
    public String findByExample(@RequestParam("name") String name, Model model) {
        Room room = new Room();
        room.setName(name);
        List<Room> rooms = roomService.findByExample(room);
        model.addAttribute("rooms", rooms);
        return "/room/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Msg msg = roomService.deleteById(id);
        if (msg == Msg.SUCCESS)
            return "/ajaxDone";
        else
            return "/ajaxFail";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("name") String name,
                         @RequestParam("num") String num,
                         @RequestParam("type") String type) {
        Room room = new Room(id, name, type);
        room.setNum(num);
        Msg msg = roomService.update(room);
        if (msg == Msg.SUCCESS)
            return "/ajaxDone";
        else
            return "/ajaxFail";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("type") String type,
                      @RequestParam("num") String num) {
        Room room = new Room(name, type);
        room.setNum(num);
        Msg msg = roomService.add(room);
        if (msg == Msg.SUCCESS)
            return "/ajaxDone";
        else
            return "/ajaxFail";
    }

    @RequestMapping("/page/edit/{id}")
    public String pageEdit(@PathVariable("id") int id,  Model model) {
        model.addAttribute("room", roomService.getbyId(id));
        return "/room/edit";
    }

    @RequestMapping("/page/add")
    public String pageAdd() {
        return "/room/add";
    }

}
