package com.idoorSys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.idoorSys.dao.PermissionDao;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;

public class PermissionService extends BaseService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getAll()
	 */
	@Override
	public List<?> getAll() {
//		return ((PermissionDao) getBaseDao()).getAll();
		List<Permission> permissions = new ArrayList<>();
		List<Object[]> objects = getBaseDao()
				.execSqlQuery(
						"select Permission.id, PermissionUser.cardNum ,Room.name, Permission.type, PermissionUser.name as pname from "
								+ "Permission,Room,PermissionUser"
								+ " where "
								+ "Permission.room_id=Room.id"
								+ " and "
								+ "Permission.permissionUser_cardNum = PermissionUser.cardNum"
								+ " ORDER BY Permission.id DESC"
				);
		for (Object[] object : objects) {
			System.out.println(object.length);
			System.out.println(Arrays.toString(object));
			Permission permission = new Permission();
			permission.setId(Long.parseLong(object[0].toString()));
			PermissionUser permissionUser = new PermissionUser();
			permissionUser.setCardNum(object[1].toString());
			permissionUser.setName(object[4].toString());
			permission.setPermissionUser(permissionUser);
			Room room = new Room();
			room.setName(object[2].toString());
			permission.setRoom(room);
			permission.setType(object[3].toString());
			permissions.add(permission);
		}
		return permissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#preAdd()
	 */
	@Override
	public void preAdd() {
		// getBaseDao().save(new Permission(new Room("aa","bb"),new
		// PermissionUser("aa","bb","cc","dd"),1));
		// getBaseDao().save(new Permission(new Room("aa","bb"),new
		// PermissionUser("aa","bb","cc","dd"),2));
		// getBaseDao().save(new Permission(new Room("aa","bb"),new
		// PermissionUser("aa","bb","cc","dd"),3));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#deleteById(long)
	 */
	@Override
	public Msg deleteById(long id) {
		return getBaseDao().deleteById(Permission.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getbyId(long)
	 */
	@Override
	public Object getbyId(long id) {
		return getBaseDao().findById(Permission.class, id);
	}

	public List<Permission> findByCondition(String userName, String roomName) {
		// TODO Auto-generated method stub
		if (userName == null || userName.equals("")) {
			userName = "null";
		}
		if (roomName == null || roomName.equals("")) {
			roomName = "null";
		}
		List<Permission> permissions = new ArrayList<>();
		List<Object[]> objects = getBaseDao()
				.execSqlQuery(
						"select Permission.id,PermissionUser.cardNum, Room.name,  Permission.type, PermissionUser.name as pname from "
								+ "Permission,Room,PermissionUser"
								+ " where "
								+ "Permission.room_id=Room.id"
								+ " and "
								+ "Permission.permissionUser_cardNum = PermissionUser.cardNum"
								+ " and "
								+ "(Room.name like '%"
								+ roomName
								+ "%'"
								+ " or "
								+ "PermissionUser.name like '%"
								+ userName + "%')"
								+ "ORDER BY Permission.id DESC");
		for (Object[] object : objects) {
			Permission permission = new Permission();
			permission.setId(Long.parseLong(object[0].toString()));
			PermissionUser permissionUser = new PermissionUser();
			permissionUser.setCardNum(object[1].toString());
			permissionUser.setName(object[4].toString());
			permission.setPermissionUser(permissionUser);
			Room room = new Room();
			room.setName(object[2].toString());
			permission.setRoom(room);
			permission.setType(object[3].toString());
			permissions.add(permission);
		}
		return permissions;
	}

}
