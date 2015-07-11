package com.idoorSys.service;

import java.util.ArrayList;
import java.util.List;

import com.idoorSys.dao.PermissionDao;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理刷卡用户权限
 */
@Service
public class PermissionService {
	@Resource
	private PermissionDao dao;

	public List<Permission> getAll() {
		return getPageAll(0, -1);
	}
	public List<Permission> getPageAll(int up, int size) {
		List<Permission> permissions = new ArrayList<>();
		List<Object[]> objects = dao
				.execSqlQuery(
						"select Permission.id, PermissionUser.cardNum ,Room.name, Permission.type, PermissionUser.name as pname from "
								+ "Permission,Room,PermissionUser"
								+ " where "
								+ "Permission.room_id=Room.id"
								+ " and "
								+ "Permission.permissionUser_cardNum = PermissionUser.cardNum"
								+ " ORDER BY Permission.id DESC"
								+ (size < 0 || up < 0 ? "" : " LIMIT " + size + " OFFSET " + up)
				);
		for (Object[] object : objects) {
			Permission permission = new Permission();
			permission.setId((Integer)object[0]);
			PermissionUser permissionUser = new PermissionUser();
			permissionUser.setCard_num(object[1].toString());
			permissionUser.setName(object[4].toString());
			permission.setPermission_user(permissionUser);
			Room room = new Room();
			room.setName(object[2].toString());
			permission.setRoom(room);
			permission.setType(object[3].toString());
			permissions.add(permission);
		}
		return permissions;
	}

	public Msg deleteById(int id) {
		return dao.deleteById(Permission.class, id);
	}

	public Object getbyId(int id) {
		return dao.findById(Permission.class, id);
	}

	public List<Permission> findByCondition(String userName, String roomName) {
		userName = userName == null || userName.equals("")? null: userName;
		roomName = roomName == null || roomName.equals("")? null: roomName;
		List<Permission> permissions = new ArrayList<>();
		List<Object[]> objects = dao
				.execSqlQuery(
						"select Permission.id,PermissionUser.cardNum, Room.name,  Permission.type, PermissionUser.name as pname from "
								+ "Permission,Room,PermissionUser"
								+ " where "
								+ "Permission.room_id=Room.id"
								+ " and "
								+ "Permission.permissionUser_cardNum = PermissionUser.cardNum"
								+ (userName == null ? "" : (" and "
								+ "PermissionUser.name like '%" + userName + "%'"))
								+ (roomName == null ? "" : (" and "
								+ "Room.name like '%" + roomName + "%'"))
								+ "ORDER BY Permission.id DESC");
		for (Object[] object : objects) {
			Permission permission = new Permission();
			permission.setId((Integer)object[0]);
			PermissionUser permissionUser = new PermissionUser();
			permissionUser.setCard_num(object[1].toString());
			permissionUser.setName(object[4].toString());
			permission.setPermission_user(permissionUser);
			Room room = new Room();
			room.setName(object[2].toString());
			permission.setRoom(room);
			permission.setType(object[3].toString());
			permissions.add(permission);
		}
		return permissions;
	}

	public Msg update(Permission permission) {
		return dao.update(permission);
	}

	public Msg add(Permission permission) {
		return dao.save(permission);
	}
}
