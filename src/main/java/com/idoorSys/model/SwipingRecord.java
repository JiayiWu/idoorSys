package com.idoorSys.model;

import java.sql.Timestamp;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * SwipingRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "swiping_record")
public class SwipingRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cardid;
	private Timestamp swipingTime;

	private String roomNum;
	private String doorNum;

	private String userName;
	private String roomName;

	// Constructors

	/** default constructor */
	public SwipingRecord() {
	}

	/** minimal constructor */
	public SwipingRecord(Timestamp swipingTime) {
		this.swipingTime = swipingTime;
	}

	/** full constructor */
	public SwipingRecord(String cardid, Timestamp swipingTime) {
		this.cardid = cardid;
		this.swipingTime = swipingTime;
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

	@Column(name = "cardid", length = 100)
	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	@Column(name = "swiping_time", nullable = false, length = 19)
	public Timestamp getSwipingTime() {
		return this.swipingTime;
	}

	public void setSwipingTime(Timestamp swipingTime) {
		this.swipingTime = swipingTime;
	}

	@Column(name = "room_num")
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "door_num")
	public String getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "SwipingRecord{" +
				"id=" + id +
				", cardid='" + cardid + '\'' +
				", swipingTime=" + swipingTime +
				", roomNum='" + roomNum + '\'' +
				", doorNum='" + doorNum + '\'' +
				", userName='" + userName + '\'' +
				", roomName='" + roomName + '\'' +
				'}';
	}
}