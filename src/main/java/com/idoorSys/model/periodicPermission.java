package com.idoorSys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 每周固定时间的权限
 */
@Entity
@Table(name = "periodic_permission")
public class PeriodicPermission implements Serializable {

    private int id;
    private Room room;
    private PermissionUser permission_user;
    private char day_of_week;
    private Time begin_time;
    private Time end_time;
    private Timestamp timetag;

    public Timestamp getTimetag() {
        return timetag;
    }

    public void setTimetag(Timestamp timetag) {
        this.timetag = timetag;
    }

    public PeriodicPermission(){}

    public PeriodicPermission(int id, Room room, PermissionUser permission_user, char day_of_week, Time begin_time, Time end_time) {
        this.id = id;
        this.room = room;
        this.permission_user = permission_user;
        this.day_of_week = day_of_week;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }

    public PeriodicPermission(Room room, PermissionUser permission_user, char day_of_week, Time begin_time, Time end_time) {
        this.room = room;
        this.permission_user = permission_user;
        this.day_of_week = day_of_week;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @JoinColumn(name="card_num", referencedColumnName = "card_num")
    public PermissionUser getPermission_user() {
        return permission_user;
    }

    public void setPermission_user(PermissionUser permissionUser) {
        this.permission_user = permissionUser;
    }

    public char getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(char dayOfWeek) {
        if (dayOfWeek< '1' || dayOfWeek> '7') {
            throw new IllegalArgumentException(dayOfWeek+" must between 1 to 7");
        }
        this.day_of_week = dayOfWeek;
    }

    public Time getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Time beginTime) {
        this.begin_time = beginTime;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time endTime) {
        this.end_time = endTime;
    }
}
