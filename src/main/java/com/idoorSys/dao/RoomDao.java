package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Room;

public class RoomDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Room> getAll() {
		return (List<Room>) super.getAll(Room.class);
	}

}
