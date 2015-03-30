package com.idoorSys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Super entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "super")
public class Super implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String username;
	private String roomNum;
	private String previligeType;

	// Constructors

	/** default constructor */
	public Super() {
	}

	/** full constructor */
	public Super(String userid, String username, String roomNum,
			String previligeType) {
		this.userid = userid;
		this.username = username;
		this.roomNum = roomNum;
		this.previligeType = previligeType;
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

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "room_num", length = 100)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "previlige_type", length = 100)
	public String getPreviligeType() {
		return this.previligeType;
	}

	public void setPreviligeType(String previligeType) {
		this.previligeType = previligeType;
	}

}