package com.idoorSys.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Device entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device")
public class Device implements java.io.Serializable {

	// Fields
	public enum State {
		on, of
	}
	
	private Integer id;
	private String roomNo;
	private String frontDoorState;
	private String backDoorState;
//	private String deskNum;
	private String deskState;	// json map
//	private String lightNum;
	private String lightState;	// json map

	// State Setter
	public void setDeskState(String deskNo, State state) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, String> map = mapper.readValue(deskState, Map.class);
			map.put(deskNo, state.toString());
			this.deskState = mapper.writeValueAsString(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setLightState(String lightNo, State state) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, String> map = mapper.readValue(lightState, Map.class);
			map.put(lightNo, state.toString());
			this.lightState = mapper.writeValueAsString(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// StateMap generator
	public Map<String, String> generateDeskStateMap() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		try {
			map = mapper.readValue(deskState, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	public Map<String, String> generateLightStateMap() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		try {
			map = mapper.readValue(lightState, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(String roomNo, String frontDoorState, String backDoorState,
			String deskState, String lightState) {
		this.roomNo = roomNo;
		this.frontDoorState = frontDoorState;
		this.backDoorState = backDoorState;
//		this.deskNum = deskNum;
		this.deskState = deskState;
//		this.lightNum = lightNum;
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
	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Column(name = "front_door_state", length = 2)
	public String getFrontDoorState() {
		return this.frontDoorState;
	}

	public void setFrontDoorState(String frontDoorState) {
		this.frontDoorState = frontDoorState;
	}

	@Column(name = "back_door_state", length = 2)
	public String getBackDoorState() {
		return this.backDoorState;
	}

	public void setBackDoorState(String backDoorState) {
		this.backDoorState = backDoorState;
	}

//	@Column(name = "desk_num", length = 10)
//	public String getDeskNum() {
//		return this.deskNum;
//	}

//	public void setDeskNum(String deskNum) {
//		this.deskNum = deskNum;
//	}

	@Column(name = "desk_state", length = 256)
	public String getDeskState() {
		return this.deskState;
	}

	public void setDeskState(String deskState) {
		this.deskState = deskState;
	}

//	@Column(name = "light_num", length = 10)
//	public String getLightNum() {
//		return this.lightNum;
//	}

//	public void setLightNum(String lightNum) {
//		this.lightNum = lightNum;
//	}

	@Column(name = "light_state", length = 256)
	public String getLightState() {
		return this.lightState;
	}

	public void setLightState(String lightState) {
		this.lightState = lightState;
	}

}