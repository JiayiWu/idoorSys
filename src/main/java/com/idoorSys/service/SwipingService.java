package com.idoorSys.service;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.SwipingDao;
import com.idoorSys.model.SwipingRecord;
import com.idoorSys.utils.Msg;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ezio on 4/19/2015.
 */
public class SwipingService {
    private BaseDao baseDao;
    
    public BaseDao getBaseDao() {
        return this.baseDao;
    }
    
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    
    public List<?> getAll() {
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, PermissionUser.name, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,PermissionUser,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) = PermissionUser.cardNum"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwipingTime(((Timestamp) object[1]));
            record.setUserName(object[2].toString());
            record.setCardid(object[3].toString());
            record.setRoomName(object[4].toString());
            records.add(record);
        }
        return records;
    }

    public List<?> getAnonymousRecords() {
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) not in (select PermissionUser.cardNum from PermissionUser)"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwipingTime(((Timestamp) object[1]));
            record.setCardid(object[2].toString());
            record.setRoomName(object[3].toString());
            record.setUserName("anonymous");
            records.add(record);
        }
        return records;
    }

    
    public List<?> getByExample(String roomName, String userName, Timestamp startTime, Timestamp endTime) {
        userName = userName==null || userName.equals("")? null: userName;
        roomName = roomName==null || roomName.equals("")? null: roomName;
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, PermissionUser.name, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,PermissionUser,Room"
                                + " where "
                                + "substring(swiping_record.cardid, 3, 6) = PermissionUser.cardNum"
                                + " and "
                                + "swiping_record.room_Num = Room.nameEn"
                                + (userName == null ? "": (" and "
                                + "PermissionUser.name like '%" + userName +"%'"))
                                + (roomName == null ? "": (" and "
                                + "Room.name like '%"+roomName+"%'"))
                                + (startTime == null ? "": (" and "
                                + "swiping_record.swiping_Time >= '"+startTime+"'"))
                                + (endTime == null ? "": (" and "
                                + "swiping_record.swiping_Time <= '"+endTime+"'"))
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwipingTime(((Timestamp) object[1]));
            record.setUserName(object[2].toString());
            record.setCardid(object[3].toString());
            record.setRoomName(object[4].toString());
            records.add(record);
        }
        return records;
    }

    public List<?> getAnonymousByExample(String roomName, Timestamp startTime, Timestamp endTime) {
        List<SwipingRecord> records = new ArrayList<>();
        List<Object[]> objects = getBaseDao()
                .execSqlQuery(
                        "select swiping_record.id, swiping_record.swiping_Time, swiping_record.cardid, Room.name as rname from "
                                + "swiping_record,Room"
                                + " where "
                                + "swiping_record.room_Num = Room.nameEn"
                                + " ORDER BY swiping_record.id DESC"
                );
        for (Object[] object : objects) {
            SwipingRecord record = new SwipingRecord();
            record.setId(Integer.parseInt(object[0].toString()));
            record.setSwipingTime(((Timestamp) object[1]));
            record.setCardid(object[2].toString());
            record.setRoomName(object[3].toString());
            record.setUserName("anonymous");
            records.add(record);
        }
        return records;
    }
}
