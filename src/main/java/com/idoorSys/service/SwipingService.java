package com.idoorSys.service;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.SwipingDao;
import com.idoorSys.model.SwipingRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ezio on 4/19/2015.
 */
@Service
public class SwipingService {
    @Resource
    private SwipingDao dao;
    
    public List<SwipingRecord> getAll() {
        return getPageAll(0, -1);
    }
    public List<SwipingRecord> getPageAll(int up, int size) {
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = dao
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, PermissionUser.name, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,PermissionUser,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) = PermissionUser.cardNum"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + " ORDER BY swiping_record.id DESC"
                                + (size < 0 || up < 0 ? "" : " LIMIT " + size + " OFFSET " + up)
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwiping_time(((Timestamp) object[1]));
            record.setUser_name(object[2].toString());
            record.setCard_id(object[3].toString());
            record.setRoom_name(object[4].toString());
            records.add(record);
        }
        return records;
    }


    public List<SwipingRecord> getAnonymousRecords() {
        return getPageAnonymousRecords(0, -1);
    }
    public List<SwipingRecord> getPageAnonymousRecords(int up, int size) {
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = dao
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) not in (select PermissionUser.cardNum from PermissionUser)"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + " ORDER BY swiping_record.id DESC"
                                + (size < 0 || up < 0 ? "" : " LIMIT " + size + " OFFSET " + up)
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwiping_time(((Timestamp) object[1]));
            record.setCard_id(object[2].toString());
            record.setRoom_name(object[3].toString());
            record.setUser_name("anonymous");
            records.add(record);
        }
        return records;
    }

    
    public List<SwipingRecord> getByExample(String roomName, String userName, Timestamp startTime, Timestamp endTime) {
        userName = userName==null || userName.equals("")? null: userName;
        roomName = roomName==null || roomName.equals("")? null: roomName;
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = dao
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, PermissionUser.name, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,PermissionUser,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) = PermissionUser.cardNum"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + (userName == null ? "" : (" and "
                                + "PermissionUser.name like '%" + userName + "%'"))
                                + (roomName == null ? "" : (" and "
                                + "Room.name like '%" + roomName + "%'"))
                                + (startTime == null ? "" : (" and "
                                + "swiping_record.swiping_Time >= '" + startTime + "'"))
                                + (endTime == null ? "" : (" and "
                                + "swiping_record.swiping_Time <= '" + endTime + "'"))
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwiping_time(((Timestamp) object[1]));
            record.setUser_name(object[2].toString());
            record.setCard_id(object[3].toString());
            record.setRoom_name(object[4].toString());
            records.add(record);
        }
        return records;
    }

    public List<SwipingRecord> getAnonymousByExample(String roomName, Timestamp startTime, Timestamp endTime) {
        roomName = roomName==null || roomName.equals("")? null: roomName;
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = dao
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,Room"
                                + " where "
                                + "swiping_record.room_Num = Room.nameEn"
                                + (roomName == null ? "" : (" and "
                                + "Room.name like '%" + roomName + "%'"))
                                + (startTime == null ? "" : (" and "
                                + "swiping_record.swiping_Time >= '" + startTime + "'"))
                                + (endTime == null ? "" : (" and "
                                + "swiping_record.swiping_Time <= '" + endTime + "'"))
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwiping_time(((Timestamp) object[1]));
            record.setCard_id(object[2].toString());
            record.setRoom_name(object[3].toString());
            record.setUser_name("anonymous");
            records.add(record);
        }
        return records;
    }
}
