package com.idoorSys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 超级卡信息
 */
@Entity
@Table(name = "super_card")
public class SuperCard {
    private int id;
    private String card_id;
    private char room_state;
    private char door_num;
    private int room_id;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String cardId) {
        this.card_id = cardId;
    }

    public char getRoom_state() {
        return room_state;
    }

    public void setRoom_state(char roomState) {
        this.room_state = roomState;
    }

    public char getDoor_num() {
        return door_num;
    }

    public void setDoor_num(char doorNum) {
        this.door_num = doorNum;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int roomId) {
        this.room_id = roomId;
    }
}
