package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Room;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unchecked")
public class RoomDao extends BaseDao {
	public List<Room> getAll() {
		return (List<Room>) super.getAll(Room.class);
	}
	public List<Room> getPageAll(int up, int size) {
		return (List<Room>) super.getPageAll(Room.class, up, size);
	}
	public List<Room> findByExample(Room room) {
		return (List<Room>)super.findByExample(room);
	}

}
