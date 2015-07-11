package com.idoorSys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 网关组号与嵌入式控制中心配对关系，（由嵌入式控制中心添加）
 * Created by Ezio on 7/9/2015.
 */
@Entity
@Table(name = "mac_pan")
public class MacPan {
    private int id;
    private String pan_id;
    private String mac;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPan_id() {
        return pan_id;
    }

    public void setPan_id(String pan_id) {
        this.pan_id = pan_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
