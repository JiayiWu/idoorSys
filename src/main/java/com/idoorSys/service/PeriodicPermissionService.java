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
                        "select periodic_permission.id" +
                                ", room.name as rName" +
                                ", periodic_permission.day_of_week" +
                                ", periodic_permission.begin_time" +
                                ", periodic_permission.end_time" +
                                ", permission_user.name" +
                                ", permission_user.card_num" +
                                " FROM periodic_permission" +
                                " JOIN room ON room.id = periodic_permission.room_id" +
                                " JOIN permission_user ON permission_user.card_num = periodic_permission.card_num"
                                + " ORDER BY periodic_permission.id DESC"
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
                        "select periodic_permission.id" +
                                ", room.name as rName" +
                                ", periodic_permission.day_of_week" +
                                ", periodic_permission.begin_time" +
                                ", periodic_permission.end_time" +
                                ", permission_user.name" +
                                ", permission_user.card_num" +
                                " FROM periodic_permission" +
                                " JOIN room ON room.id = periodic_permission.room_id" +
                                " JOIN permission_user ON permission_user.card_num = periodic_permission.card_num"
                                + (userName == null ? "": (" and "
                                + "permission_user.name like '%" + userName +"%'"))
                                + (roomName == null ? "": (" and "
                                + "room.name like '%" + roomName +"%'"))
                                + (dayOfWeek<1 || dayOfWeek>7 ? "": (" and "
                                + "periodic_permission.day_of_week ="+dayOfWeek))
                                + " ORDER BY periodic_permission.id DESC"
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
