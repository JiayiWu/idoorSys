package com.idoorSys.model;

import javax.persistence.*;

/**
 * 刷卡用户类别
 */
@Entity
@Table(name = "ugroup")
public class UGroup {
    private int id;
    private String name;

    public UGroup(){}
    public UGroup(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
