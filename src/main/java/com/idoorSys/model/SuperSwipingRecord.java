package com.idoorSys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 超级卡刷卡记录
 */
@Entity
@Table(name = "super_swiping_record")
public class SuperSwipingRecord {
    private Integer id;
    private String card_id;
    private Timestamp swiping_time;

    private String room_num;
    private String door_num;

    private String user_name;
    private String room_name;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String cardid) {
        this.card_id = cardid;
    }

    public Timestamp getSwiping_time() {
        return swiping_time;
    }

    public void setSwiping_time(Timestamp swipingTime) {
        this.swiping_time = swipingTime;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String roomNum) {
        this.room_num = roomNum;
    }

    public String getDoor_num() {
        return door_num;
    }

    public void setDoor_num(String doorNum) {
        this.door_num = doorNum;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        this.user_name = userName;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String roomName) {
        this.room_name = roomName;
    }
}
