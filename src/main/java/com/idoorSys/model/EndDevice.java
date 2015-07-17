package com.idoorSys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 终端设备，（由嵌入式控制中心添加，在网页端无业务逻辑或表现)
 * Created by Ezio on 7/9/2015.
 */
@Entity
@Table(name = "end_device")
public class EndDevice {
    private int id;
    private String room_num;
    private String device_name;
    private String identity;
    private String group_route;
    private String dst_route;
    private String short_addr;
    private String ext_addr;
    private String pan_id;
    private String device_num;
    private char device_state;
    private String device_ext_timer;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getGroup_route() {
        return group_route;
    }

    public void setGroup_route(String group_route) {
        this.group_route = group_route;
    }

    public String getDst_route() {
        return dst_route;
    }

    public void setDst_route(String dst_route) {
        this.dst_route = dst_route;
    }

    public String getShort_addr() {
        return short_addr;
    }

    public void setShort_addr(String short_addr) {
        this.short_addr = short_addr;
    }

    public String getExt_addr() {
        return ext_addr;
    }

    public void setExt_addr(String ext_addr) {
        this.ext_addr = ext_addr;
    }

    public String getPan_id() {
        return pan_id;
    }

    public void setPan_id(String pan_id) {
        this.pan_id = pan_id;
    }

    public String getDevice_num() {
        return device_num;
    }

    public void setDevice_num(String device_num) {
        this.device_num = device_num;
    }

    public char getDevice_state() {
        return device_state;
    }

    public void setDevice_state(char device_state) {
        this.device_state = device_state;
    }

    public String getDevice_ext_timer() {
        return device_ext_timer;
    }

    public void setDevice_ext_timer(String device_ext_timer) {
        this.device_ext_timer = device_ext_timer;
    }
}
