package com.idoorSys.service;

import com.idoorSys.dao.PeriodicPermissionDao;
import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理周期性权限
 */
@Service
public class PeriodicPermissionService {
    @Resource
    private PeriodicPermissionDao dao;

    public List<PeriodicPermission> getAll() {
        return getPageAll(0, -1);
    }
    public List<PeriodicPermission> getPageAll(int up,int size) {
        List<PeriodicPermission> permissions = new ArrayList<>();
        List<Object[]> objects = dao
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
                                + (size < 0 || up < 0 ? "" : " LIMIT " + size + " OFFSET " + up)
                );
        for (Object[] object : objects) {
            PeriodicPermission permission = new PeriodicPermission();
            permission.setId((Integer) object[0]);
            Room room = new Room();
            room.setName(object[1].toString());
            permission.setRoom(room);
            permission.setDay_of_week((Character) object[2]);
            permission.setBegin_time(Time.valueOf(object[3].toString()));
            permission.setEnd_time(Time.valueOf(object[4].toString()));
            PermissionUser permissionUser = new PermissionUser();
            permissionUser.setName(object[5].toString());
            permissionUser.setCard_num(object[6].toString());
            permission.setPermission_user(permissionUser);
            permissions.add(permission);
        }
        return permissions;
    }


    public Msg deleteById(int id) {
        return dao.deleteById(PeriodicPermission.class, id);
    }

    public PeriodicPermission getbyId(int id) {
        return dao.getbyId(id);
    }

    public List<PeriodicPermission> findByCondition(String userName, String roomName, int dayOfWeek) {
        userName = userName == null || userName.equals("")? null: userName;
        roomName = roomName == null || roomName.equals("")? null: roomName;
        List<PeriodicPermission> permissions = new ArrayList<>();
        List<Object[]> objects = dao
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
            permission.setId((Integer) object[0]);
            Room room = new Room();
            room.setName(object[1].toString());
            permission.setRoom(room);
            permission.setDay_of_week((Character) object[2]);
            permission.setBegin_time(Time.valueOf(object[3].toString()));
            permission.setEnd_time(Time.valueOf(object[4].toString()));
            PermissionUser permissionUser = new PermissionUser();
            permissionUser.setName(object[5].toString());
            permissionUser.setCard_num(object[6].toString());
            permission.setPermission_user(permissionUser);
            permissions.add(permission);
        }
        return permissions;
    }

    public Msg update(PeriodicPermission permission) {
        return dao.update(permission);
    }

    public Msg add(PeriodicPermission permission) {
        return dao.save(permission);
    }
}
