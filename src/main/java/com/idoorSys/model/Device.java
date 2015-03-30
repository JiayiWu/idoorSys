package com.idoorSys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Device entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device")
public class Device implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomNum;
	private String frontDoorState;
	private String backDoorState;
	private String deskNum;
	private String deskState;
	private String lightNum;
	private String lightState;

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(String roomNum, String frontDoorState, String backDoorState,
			String deskNum, String deskState, String lightNum, String lightState) {
		this.roomNum = roomNum;
		this.frontDoorState = frontDoorState;
		this.backDoorState = backDoorState;
		this.deskNum = deskNum;
		this.deskState = deskState;
		this.lightNum = lightNum;
		this.lightState = lightState;
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

	@Column(name = "room_num", length = 100)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "front_door_state", length = 1)
	public String getFrontDoorState() {
		return this.frontDoorState;
	}

	public void setFrontDoorState(String frontDoorState) {
		this.frontDoorState = frontDoorState;
	}

	@Column(name = "back_door_state", length = 1)
	public String getBackDoorState() {
		return this.backDoorState;
	}

	public void setBackDoorState(String backDoorState) {
		this.backDoorState = backDoorState;
	}

	@Column(name = "desk_num", length = 10)
	public String getDeskNum() {
		return this.deskNum;
	}

	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}

	@Column(name = "desk_state", length = 1)
	public String getDeskState() {
		return this.deskState;
	}

	public void setDeskState(String deskState) {
		this.deskState = deskState;
	}

	@Column(name = "light_num", length = 10)
	public String getLightNum() {
		return this.lightNum;
	}

	public void setLightNum(String lightNum) {
		this.lightNum = lightNum;
	}

	@Column(name = "light_state", length = 1)
	public String getLightState() {
		return this.lightState;
	}

	public void setLightState(String lightState) {
		this.lightState = lightState;
	}

}