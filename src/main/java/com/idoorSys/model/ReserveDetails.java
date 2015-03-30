package com.idoorSys.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReserveDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reserve_details")
public class ReserveDetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String roomNum;
	private Timestamp inTime;
	private Timestamp outTime;
	private String doorNum;
	private String deskNum;
	private String deskLeftRight;

	// Constructors

	/** default constructor */
	public ReserveDetails() {
	}

	/** minimal constructor */
	public ReserveDetails(Timestamp inTime, Timestamp outTime) {
		this.inTime = inTime;
		this.outTime = outTime;
	}

	/** full constructor */
	public ReserveDetails(String userid, String roomNum, Timestamp inTime,
			Timestamp outTime, String doorNum, String deskNum,
			String deskLeftRight) {
		this.userid = userid;
		this.roomNum = roomNum;
		this.inTime = inTime;
		this.outTime = outTime;
		this.doorNum = doorNum;
		this.deskNum = deskNum;
		this.deskLeftRight = deskLeftRight;
	}

	public ReserveDetails(String userid, String roomNum, String inTime,
			String outTime, String doorNum, String deskNum, String deskLeftRight) {
		// TODO Auto-generated constructor stub
		this.userid = userid;
		this.roomNum = roomNum;
		this.inTime = Timestamp.valueOf(inTime);
		this.outTime = Timestamp.valueOf(outTime);
		this.doorNum = doorNum;
		this.deskNum = deskNum;
		this.deskLeftRight = deskLeftRight;
	}

	public ReserveDetails(int id, String userid, String roomNum, String inTime,
			String outTime, String doorNum, String deskNum, String deskLeftRight) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.userid = userid;
		this.roomNum = roomNum;
		this.inTime = Timestamp.valueOf(inTime);
		this.outTime = Timestamp.valueOf(outTime);
		this.doorNum = doorNum;
		this.deskNum = deskNum;
		this.deskLeftRight = deskLeftRight;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid", length = 100)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "room_num", length = 100)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "in_time", nullable = false, length = 19)
	public Timestamp getInTime() {
		return this.inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	@Column(name = "out_time", nullable = false, length = 19)
	public Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	@Column(name = "door_num", length = 1)
	public String getDoorNum() {
		return this.doorNum;
	}

	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}

	@Column(name = "desk_num", length = 10)
	public String getDeskNum() {
		return this.deskNum;
	}

	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}

	@Column(name = "desk_left_right", length = 1)
	public String getDeskLeftRight() {
		return this.deskLeftRight;
	}

	public void setDeskLeftRight(String deskLeftRight) {
		this.deskLeftRight = deskLeftRight;
	}

}