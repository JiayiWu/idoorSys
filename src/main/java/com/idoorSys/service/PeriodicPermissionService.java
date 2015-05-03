package com.idoorSys.service;

import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ezio on 5/2/2015.
 */
public class PeriodicPermissionService extends BaseService {
    @Override
    public List<?> getAll() {
        List<PeriodicPermission> permissions = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select PeriodicPermission.id" +
                                ", Room.name as rName" +
                                ", PeriodicPermission.dayOfWeek" +
                                ", PeriodicPermission.beginTime" +
                                ", PeriodicPermission.endTime" +
                                ", PermissionUser.name" +
                                ", PermissionUser.cardNum" +
                                " FROM PeriodicPermission" +
                                " JOIN Room ON Room.id = PeriodicPermission.room_id" +
                                " JOIN PermissionUser ON PermissionUser.cardNum = PeriodicPermission.permissionUser_cardNum"
                                + " ORDER BY PeriodicPermission.id DESC"
                );
        for (Object[] object : objects) {
            PeriodicPermission permission = new PeriodicPermission();
            permission.setId(((BigInteger) object[0]).longValue());
            Room room = new Room();
            room.setName(object[1].toString());
            permission.setRoom(room);
            permission.setDayOfWeek((Integer) object[2]);
            permission.setBeginTime(Time.valueOf(object[3].toString()));
            permission.setEndTime(Time.valueOf(object[4].toString()));
            PermissionUser permissionUser = new PermissionUser();
            permissionUser.setName(object[5].toString());
            permissionUser.setCardNum(object[6].toString());
            permission.setPermissionUser(permissionUser);
            permissions.add(permission);
        }
        return permissions;
    }

    @Override
    public void preAdd() {}

    @Override
    public Msg deleteById(long id) {
        return getBaseDao().deleteById(PeriodicPermission.class, id);
    }

    @Override
    public Object getbyId(long id) {
        return getBaseDao().findById(PeriodicPermission.class, id);
    }

    public List<?> findByCondition(String userName, String roomName, int dayOfWeek) {
        userName = userName == null || userName.equals("")? null: userName;
        roomName = roomName == null || roomName.equals("")? null: roomName;
        List<PeriodicPermission> permissions = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select PeriodicPermission.id" +
                                ", Room.name as rName" +
                                ", PeriodicPermission.dayOfWeek" +
                                ", PeriodicPermission.beginTime" +
                                ", PeriodicPermission.endTime" +
                                ", PermissionUser.name" +
                                ", PermissionUser.cardNum" +
                                " FROM PeriodicPermission" +
                                " JOIN Room ON Room.id = PeriodicPermission.room_id" +
                                " JOIN PermissionUser ON PermissionUser.cardNum = PeriodicPermission.permissionUser_cardNum"
                                + (userName == null ? "": (" and "
                                + "PermissionUser.name like '%" + userName +"%'"))
                                + (roomName == null ? "": (" and "
                                + "Room.name like '%" + roomName +"%'"))
                                + (dayOfWeek<1 || dayOfWeek>7 ? "": (" and "
                                + "PeriodicPermission.dayOfWeek ="+dayOfWeek))
                                + " ORDER BY PeriodicPermission.id DESC"
                );
        for (Object[] object : objects) {
            PeriodicPermission permission = new PeriodicPermission();
            permission.setId(((BigInteger) object[0]).longValue());
            Room room = new Room();
            room.setName(object[1].toString());
            permission.setRoom(room);
            permission.setDayOfWeek((Integer) object[2]);
            permission.setBeginTime(Time.valueOf(object[3].toString()));
            permission.setEndTime(Time.valueOf(object[4].toString()));
            PermissionUser permissionUser = new PermissionUser();
            permissionUser.setName(object[5].toString());
            permissionUser.setCardNum(object[6].toString());
            permission.setPermissionUser(permissionUser);
            permissions.add(permission);
        }
        return permissions;
    }
}
