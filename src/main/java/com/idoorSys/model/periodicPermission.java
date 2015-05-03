package com.idoorSys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

/**
 * 每周固定时间的权限
 * Created by Ezio on 5/2/2015.
 */
@Entity
public class PeriodicPermission implements Serializable {

    private long id;
    private Room room;
    private PermissionUser permissionUser;
    private int dayOfWeek;
    private Time beginTime;
    private Time endTime;

    public PeriodicPermission(){}

    public PeriodicPermission(long id, Room room, PermissionUser permissionUser, int dayOfWeek, Time beginTime, Time endTime) {
        this.id = id;
        this.room = room;
        this.permissionUser = permissionUser;
        this.dayOfWeek = dayOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public PeriodicPermission(Room room, PermissionUser permissionUser, int dayOfWeek, Time beginTime, Time endTime) {
        this.room = room;
        this.permissionUser = permissionUser;
        this.dayOfWeek = dayOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "cardNum")
    public PermissionUser getPermissionUser() {
        return permissionUser;
    }

    public void setPermissionUser(PermissionUser permissionUser) {
        this.permissionUser = permissionUser;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        if (dayOfWeek<1 || dayOfWeek>7) {
            throw new IllegalArgumentException(dayOfWeek+" must between 1 to 7");
        }
        this.dayOfWeek = dayOfWeek;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
