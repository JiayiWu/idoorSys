package com.idoorSys.utils;

import com.idoorSys.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ezio on 7/13/2015.
 */
public class Syncronizer {
    private static final Logger log = LoggerFactory.getLogger(Syncronizer.class);
    Connection connectionM = null;
    Connection connectionS = null;

    private String dataBase;
    private String username;
    private String password;

    public static Syncronizer create(String dataBase, String username, String password) throws SQLException {
        Syncronizer syncronizer = new Syncronizer();
        syncronizer.dataBase = dataBase;
        syncronizer.username = username;
        syncronizer.password = password;
        loadDriver();
        syncronizer.connectionM = DriverManager.getConnection("jdbc:mysql://localhost:3306/idoorSysMaster", "root", "1234qwer");
        return syncronizer;
    }

    public void sync(String ip) throws SQLException {
        connectionS = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/"+dataBase, username, password);
        syncRoom(ip);
        syncPermission(ip);
        syncPeriodicPermission(ip);
        syncReserve(ip);
        connectionS.close();
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        connectionM.close();
    }

    private void syncRoom(String ip) throws SQLException {
        PreparedStatement statementM = connectionM.prepareStatement(
                "SELECT room.id, room.name, room.type, room.num, room.timetag" +
                        " from end_device join mac_pan on end_device.pan_id = mac_pan.pan_id" +
                        " join remote_controller on remote_controller.mac = mac_pan.mac" +
                        " join room on room.num = end_device.room_num" +
                        " where ip = '" + ip + "'");
        Map<Integer, Room> masterMap = new HashMap<>();
        ResultSet resultSetM = statementM.executeQuery();
        while (resultSetM.next()) {
            Room toAdd = new Room();
            toAdd.setId(resultSetM.getInt(1));
            toAdd.setName(resultSetM.getString(2));
            toAdd.setType(resultSetM.getString(3));
            toAdd.setNum(resultSetM.getString(4));
            toAdd.setTimetag(resultSetM.getTimestamp(5));
            masterMap.put(toAdd.getId(), toAdd);
        }
        PreparedStatement statementS = connectionS.prepareStatement(
                "SELECT room.id, room.name, room.type, room.num, room.timetag" +
                        " FROM room"
        );
        Map<Integer, Room> slaveMap = new HashMap<>();
        ResultSet resultSetS = statementS.executeQuery();
        while (resultSetS.next()) {
            Room toAdd = new Room();
            toAdd.setId(resultSetS.getInt(1));
            toAdd.setName(resultSetS.getString(2));
            toAdd.setType(resultSetS.getString(3));
            toAdd.setNum(resultSetS.getString(4));
            toAdd.setTimetag(resultSetS.getTimestamp(5));
            slaveMap.put(resultSetS.getInt(1), toAdd);
        }
        Set<Room> toAdd = new HashSet<>(), toUpdate = new HashSet<>(), toDelete = new HashSet<>();
        for (int id : slaveMap.keySet()) {
            if (masterMap.keySet().contains(id)) {
                if (!masterMap.get(id).getTimetag().before(slaveMap.get(id).getTimetag())) {
                    toUpdate.add(masterMap.get(id));
                }
            }
            else {
                toDelete.add(slaveMap.get(id));
            }
        }
        for (int id : masterMap.keySet()) {
            if (!slaveMap.keySet().contains(id)) {
                toAdd.add(masterMap.get(id));
            }
        }
        System.out.println(masterMap.keySet());
        System.out.println(slaveMap.keySet());
        for(Room r: toAdd) { System.out.print(r.getId() + " "); } System.out.println(" to add");
        for(Room r: toUpdate) { System.out.print(r.getId() + " "); } System.out.println(" to update");
        for(Room r: toDelete) { System.out.print(r.getId() + " "); } System.out.println(" to delete");
        connectionS.setAutoCommit(false);
        if (!toAdd.isEmpty()) {
            PreparedStatement add = connectionS.prepareStatement("insert into room(id, name, type, num, timetag) values(?,?,?,?,?)");
            for (Room r: toAdd){
                add.setInt(1, r.getId());
                add.setString(2, r.getName());
                add.setString(3, r.getType());
                add.setString(4, r.getNum());
                add.setTimestamp(5, r.getTimetag());
                add.addBatch();
            }
            add.executeBatch();
        }
        if (!toUpdate.isEmpty()) {
            PreparedStatement update = connectionS.prepareStatement("update room set name=?, type=?, num=?, timetag=? where id=?");
            for (Room r: toUpdate) {
                update.setString(1, r.getName());
                update.setString(2, r.getType());
                update.setString(3, r.getNum());
                update.setTimestamp(4, r.getTimetag());
                update.setInt(5, r.getId());
                update.addBatch();
            }
            update.executeBatch();
        }
        if (!toDelete.isEmpty()) {
            PreparedStatement delete = connectionS.prepareStatement("delete from room where id=?");
            for (Room r: toDelete) {
                delete.setInt(1, r.getId());
                delete.addBatch();
            }
            delete.executeBatch();
        }
        connectionS.commit();
    }

    private void syncPermission(String ip) throws SQLException {
        PreparedStatement statementM = connectionM.prepareStatement(
                "SELECT permission.id, permission.timetag, permission.type, permission.card_num, permission.room_id" +
                        " from end_device join mac_pan on end_device.pan_id = mac_pan.pan_id" +
                        " join remote_controller on remote_controller.mac = mac_pan.mac" +
                        " join room on room.num = end_device.room_num" +
                        " join permission on permission.room_id = room.id" +
                        " where ip = '" + ip + "'");
        Map<Integer, Permission> masterMap = new HashMap<>();
        ResultSet resultSetM = statementM.executeQuery();
        while (resultSetM.next()) {
            Permission toAdd = new Permission();
            toAdd.setId(resultSetM.getInt(1));
            toAdd.setTimetag(resultSetM.getTimestamp(2));
            toAdd.setType(resultSetM.getString(3));
            toAdd.setPermission_user(new PermissionUser(resultSetM.getString(4)));
            toAdd.setRoom(new Room(resultSetM.getInt(5)));
            masterMap.put(toAdd.getId(), toAdd);
        }
        PreparedStatement statementS = connectionS.prepareStatement(
                "SELECT permission.id, permission.timetag, permission.type, permission.card_num, permission.room_id" +
                        " FROM permission"
        );
        Map<Integer, Permission> slaveMap = new HashMap<>();
        ResultSet resultSetS = statementS.executeQuery();
        while (resultSetS.next()) {
            Permission toAdd = new Permission();
            toAdd.setId(resultSetS.getInt(1));
            toAdd.setTimetag(resultSetS.getTimestamp(2));
            toAdd.setType(resultSetS.getString(3));
            toAdd.setPermission_user(new PermissionUser(resultSetS.getString(4)));
            toAdd.setRoom(new Room(resultSetS.getInt(5)));
            slaveMap.put(toAdd.getId(), toAdd);
        }
        log.info("slaveMap.null?: "+(slaveMap == null));
        log.info("slaveMap.keyset: "+(slaveMap.keySet()));
        Set<Permission> toAdd = new HashSet<>(), toUpdate = new HashSet<>(), toDelete = new HashSet<>();
        for (int id : slaveMap.keySet()) {
            if (masterMap.keySet().contains(id)) {
                if (!masterMap.get(id).getTimetag().before(slaveMap.get(id).getTimetag())) {
                    toUpdate.add(masterMap.get(id));
                }
            } else {
                toDelete.add(slaveMap.get(id));
            }
        }
        for (int id : masterMap.keySet()) {
            if (!slaveMap.keySet().contains(id)) {
                toAdd.add(masterMap.get(id));
            }
        }
        connectionS.setAutoCommit(false);
        if (!toAdd.isEmpty()) {
            PreparedStatement add = connectionS.prepareStatement("insert into permission(id,timetag, type, card_num, room_id) values(?,?,?,?,?)");
            for (Permission p : toAdd) {
                add.setInt(1, p.getId());
                add.setTimestamp(2, p.getTimetag());
                add.setString(3, p.getType());
                add.setString(4, p.getPermission_user().getCard_num());
                add.setInt(5, p.getRoom().getId());
                add.addBatch();
            }
            add.executeBatch();
        }
        if (!toUpdate.isEmpty()) {
            PreparedStatement update = connectionS.prepareStatement("update permission set timetag=?, type=?, card_num=?, room_id=? where id=?");
            for (Permission p : toUpdate) {
                update.setInt(5, p.getId());
                update.setTimestamp(1, p.getTimetag());
                update.setString(2, p.getType());
                update.setString(3, p.getPermission_user().getCard_num());
                update.setInt(4, p.getRoom().getId());
                update.addBatch();
            }
            update.executeBatch();
        }
        if (!toDelete.isEmpty()) {
            PreparedStatement delete = connectionS.prepareStatement("delete from permission where id=?");
            for (Permission p : toDelete) {
                delete.setInt(1, p.getId());
                delete.addBatch();
            }
            delete.executeBatch();
        }
        connectionS.commit();
    }

    private void syncPeriodicPermission(String ip) throws SQLException {
        PreparedStatement statementM = connectionM.prepareStatement(
                "SELECT periodic_permission.id, periodic_permission.begin_time, periodic_permission.end_time, periodic_permission.room_id" +
                        ", periodic_permission.day_of_week, periodic_permission.timetag, periodic_permission.card_num" +
                        " from end_device join mac_pan on end_device.pan_id = mac_pan.pan_id" +
                        " join remote_controller on remote_controller.mac = mac_pan.mac" +
                        " join room on room.num = end_device.room_num" +
                        " join periodic_permission on periodic_permission.room_id = room.id" +
                        " where ip = '" + ip + "'");
        Map<Integer, PeriodicPermission> masterMap = new HashMap<>();
        ResultSet resultSetM = statementM.executeQuery();
        while (resultSetM.next()) {
            PeriodicPermission toAdd = new PeriodicPermission();
            toAdd.setId(resultSetM.getInt(1));
            toAdd.setBegin_time(resultSetM.getTime(2));
            toAdd.setEnd_time(resultSetM.getTime(3));
            toAdd.setRoom(new Room(resultSetM.getInt(4)));
            toAdd.setDay_of_week(resultSetM.getString(5).charAt(0));
            toAdd.setTimetag(resultSetM.getTimestamp(6));
            toAdd.setPermission_user(new PermissionUser(resultSetM.getString(7)));
            masterMap.put(toAdd.getId(), toAdd);
        }
        PreparedStatement statementS = connectionS.prepareStatement(
                "SELECT periodic_permission.id, periodic_permission.begin_time, periodic_permission.end_time, periodic_permission.room_id" +
                        ", periodic_permission.day_of_week, periodic_permission.timetag, periodic_permission.card_num" +
                        " FROM periodic_permission"
        );
        Map<Integer, PeriodicPermission> slaveMap = new HashMap<>();
        ResultSet resultSetS = statementS.executeQuery();
        while (resultSetS.next()) {
            PeriodicPermission toAdd = new PeriodicPermission();
            toAdd.setId(resultSetS.getInt(1));
            toAdd.setBegin_time(resultSetS.getTime(2));
            toAdd.setEnd_time(resultSetS.getTime(3));
            toAdd.setRoom(new Room(resultSetS.getInt(4)));
            toAdd.setDay_of_week(resultSetS.getString(5).charAt(0));
            toAdd.setTimetag(resultSetS.getTimestamp(6));
            toAdd.setPermission_user(new PermissionUser(resultSetS.getString(7)));
            slaveMap.put(toAdd.getId(), toAdd);
        }
        Set<PeriodicPermission> toAdd = new HashSet<>(), toUpdate = new HashSet<>(), toDelete = new HashSet<>();
        for (int id : slaveMap.keySet()) {
            if (masterMap.keySet().contains(id)) {
                if (!masterMap.get(id).getTimetag().before(slaveMap.get(id).getTimetag())) {
                    toUpdate.add(masterMap.get(id));
                }
            }
            else {
                toDelete.add(slaveMap.get(id));
            }
        }
        for (int id : masterMap.keySet()) {
            if (!slaveMap.keySet().contains(id)) {
                toAdd.add(masterMap.get(id));
            }
        }
        connectionS.setAutoCommit(false);
        if (!toAdd.isEmpty()) {
            PreparedStatement add = connectionS.prepareStatement("insert into periodic_permission(id, begin_time, end_time, room_id, day_of_week, timetag, card_num)" +
                    " values(?,?,?,?,?,?,?)");
            for (PeriodicPermission p: toAdd){
                add.setInt(1, p.getId());
                add.setTime(2, p.getBegin_time());
                add.setTime(3, p.getEnd_time());
                add.setInt(4, p.getRoom().getId());
                add.setString(5, String.valueOf(p.getDay_of_week()));
                add.setTimestamp(6, p.getTimetag());
                add.setString(7, p.getPermission_user().getCard_num());
                add.addBatch();
            }
            add.executeBatch();
        }
        if (!toUpdate.isEmpty()) {
            PreparedStatement update = connectionS.prepareStatement("update periodic_permission set begin_time=?, end_time=?, room_id=?, day_of_week=?, timetag=?, card_num=? where id=?");
            for (PeriodicPermission p: toUpdate) {
                update.setTime(1, p.getBegin_time());
                update.setTime(2, p.getEnd_time());
                update.setInt(3, p.getRoom().getId());
                update.setString(4, String.valueOf(p.getDay_of_week()));
                update.setTimestamp(5, p.getTimetag());
                update.setString(6, p.getPermission_user().getCard_num());
                update.setInt(7, p.getId());
                update.addBatch();
            }
            update.executeBatch();
        }
        if (!toDelete.isEmpty()) {
            PreparedStatement delete = connectionS.prepareStatement("delete from periodic_permission where id=?");
            for (PeriodicPermission p: toDelete) {
                delete.setInt(1, p.getId());
                delete.addBatch();
            }
            delete.executeBatch();
        }
        connectionS.commit();
    }

    private void syncReserve(String ip) throws SQLException {
        PreparedStatement statementM = connectionM.prepareStatement(
                "SELECT reserve.id, reserve.room_num, reserve.begin_time, reserve.end_time, reserve.seat_id" +
                        " ,reserve.card_num ,reserve.old_id , reserve.timetag" +
                        " from end_device join mac_pan on end_device.pan_id = mac_pan.pan_id" +
                        " join remote_controller on remote_controller.mac = mac_pan.mac" +
                        " join reserve on reserve.room_num = end_device.room_num" +
                        " where ip = '" + ip + "'");
        Map<Integer, Reserve> masterMap = new HashMap<>();
        ResultSet resultSetM = statementM.executeQuery();
        while (resultSetM.next()) {
            Reserve toAdd = new Reserve();
            toAdd.setId(resultSetM.getInt(1));
            toAdd.setRoom_num(resultSetM.getString(2));
            toAdd.setBegin_time(resultSetM.getDate(3));
            toAdd.setEnd_time(resultSetM.getDate(4));
            toAdd.setSeat_id(resultSetM.getString(5));
            toAdd.setCard_num(resultSetM.getString(6));
            toAdd.setOld_id(resultSetM.getString(7));
            toAdd.setTimetag(resultSetM.getTimestamp(8));
            masterMap.put(toAdd.getId(), toAdd);
        }
        PreparedStatement statementS = connectionS.prepareStatement(
                "SELECT reserve.id, reserve.room_num, reserve.begin_time, reserve.end_time, reserve.seat_id" +
                        " ,reserve.card_num ,reserve.old_id , reserve.timetag" +
                        " FROM reserve"
        );
        Map<Integer, Reserve> slaveMap = new HashMap<>();
        ResultSet resultSetS = statementS.executeQuery();
        while (resultSetS.next()) {
            Reserve toAdd = new Reserve();
            toAdd.setId(resultSetS.getInt(1));
            toAdd.setRoom_num(resultSetS.getString(2));
            toAdd.setBegin_time(resultSetS.getDate(3));
            toAdd.setEnd_time(resultSetS.getDate(4));
            toAdd.setSeat_id(resultSetS.getString(5));
            toAdd.setCard_num(resultSetS.getString(6));
            toAdd.setOld_id(resultSetS.getString(7));
            toAdd.setTimetag(resultSetS.getTimestamp(8));
            slaveMap.put(toAdd.getId(), toAdd);
        }
        Set<Reserve> toAdd = new HashSet<>(), toUpdate = new HashSet<>(), toDelete = new HashSet<>();
        for (int id : slaveMap.keySet()) {
            if (masterMap.keySet().contains(id)) {
                if (!masterMap.get(id).getTimetag().before(slaveMap.get(id).getTimetag())) {
                    toUpdate.add(masterMap.get(id));
                }
            } else {
                toDelete.add(slaveMap.get(id));
            }
        }
        for (int id : masterMap.keySet()) {
            if (!slaveMap.keySet().contains(id)) {
                toAdd.add(masterMap.get(id));
            }
        }
        // manipulate toAdd toUpdate toDelete
        connectionS.setAutoCommit(false);
        if (!toAdd.isEmpty()) {
            PreparedStatement add = connectionS.prepareStatement("insert into reserve(id, room_num, begin_time, end_time, seat_id, card_num, old_id, timetag)" +
                    " values(?,?,?,?,?,?,?,?)");
            for (Reserve r : toAdd) {
                add.setInt(1, r.getId());
                add.setString(2, r.getRoom_num());
                add.setTimestamp(3, new Timestamp(r.getBegin_time().getTime()));
                add.setTimestamp(4, new Timestamp(r.getEnd_time().getTime()));
                add.setString(5, r.getSeat_id());
                add.setString(6, r.getCard_num());
                add.setString(7, r.getOld_id());
                add.setTimestamp(8, r.getTimetag());
                add.addBatch();
            }
            add.executeBatch();
        }
        if (!toUpdate.isEmpty()) {
            PreparedStatement update = connectionS.prepareStatement("update reserve set room_num=?, begin_time=?, end_time=?, seat_id=?, card_num=?, old_id=?, timetag=? where id=?");
            for (Reserve r : toUpdate) {
                update.setString(1, r.getRoom_num());
                update.setTimestamp(2, new Timestamp(r.getBegin_time().getTime()));
                update.setTimestamp(3, new Timestamp(r.getEnd_time().getTime()));
                update.setString(4, r.getSeat_id());
                update.setString(5, r.getCard_num());
                update.setString(6, r.getOld_id());
                update.setTimestamp(7, r.getTimetag());
                update.setInt(8, r.getId());
                update.addBatch();
            }
            update.executeBatch();
        }
        if (!toDelete.isEmpty()) {
            PreparedStatement delete = connectionS.prepareStatement("delete from reserve where id=?");
            for (Reserve r : toDelete) {
                delete.setInt(1, r.getId());
                delete.addBatch();
            }
            delete.executeBatch();
        }
        connectionS.commit();
    }
}
