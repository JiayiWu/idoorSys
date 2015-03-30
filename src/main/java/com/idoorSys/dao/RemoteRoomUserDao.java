package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.RemoteRoomUser;

public class RemoteRoomUserDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<RemoteRoomUser> getAll() {
		return (List<RemoteRoomUser>) super.getAll(RemoteRoomUser.class);
	}
}
