package com.idoorSys.service;

import com.idoorSys.dao.RoomDao;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理房间信息
 */
@Service
public class RoomService {
    @Resource
    private RoomDao dao;

    public List<Room> getAll() {
        return dao.getAll();
    }

    public List<Room> getPageAll(int up, int size) {
        return dao.getPageAll(up, size);
    }

    public Msg deleteById(int id) {
        return dao.deleteById(Room.class, id);
    }

    public Object getbyId(int id) {
        return dao.findById(Room.class, id);
    }

    public Msg update(Room room) {
        return dao.update(room);
    }

    public Msg add(Room room) {
        return  dao.save(room);
    }

    public List<Room> findByExample(Room room) {
        return dao.findByExample(room);
    }
}
