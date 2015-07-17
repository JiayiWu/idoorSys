package com.idoorSys.model;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 * 刷卡记录
 */
@Entity
@Table(name = "swiping_record")
public class SwipingRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String card_id;
	private Timestamp swiping_time;

	private String room_num;
	private String door_num;

	private String user_name;
	private String room_name;

	// Constructors

	/** default constructor */
	public SwipingRecord() {
	}

	/** minimal constructor */
	public SwipingRecord(Timestamp swiping_time) {
		this.swiping_time = swiping_time;
	}

	/** full constructor */
	public SwipingRecord(String card_id, Timestamp swiping_time) {
		this.card_id = card_id;
		this.swiping_time = swiping_time;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 100)
	public String getCard_id() {
		return this.card_id;
	}

	public void setCard_id(String cardid) {
		this.card_id = cardid;
	}

	@Column(nullable = false, length = 19)
	public Timestamp getSwiping_time() {
		return this.swiping_time;
	}

	public void setSwiping_time(Timestamp swipingTime) {
		this.swiping_time = swipingTime;
	}

	@Column(name = "room_num")
	public String getRoom_num() {
		return room_num;
	}

	public void setRoom_num(String roomNum) {
		this.room_num = roomNum;
	}

	@Column(name = "door_num")
	public String getDoor_num() {
		return door_num;
	}

	public void setDoor_num(String doorNum) {
		this.door_num = doorNum;
	}

	@Transient
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Transient
	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	@Override
	public String toString() {
		return "SwipingRecord{" +
				"id=" + id +
				", cardid='" + card_id + '\'' +
				", swipingTime=" + swiping_time +
				", roomNum='" + room_num + '\'' +
				", doorNum='" + door_num + '\'' +
				", user_name='" + user_name + '\'' +
				", room_name='" + room_name + '\'' +
				'}';
	}
}