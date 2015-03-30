package com.idoorSys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class RemoteRoomUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String username;
	private String cardid;
	private String userIdentity;

	// Constructors

	/** default constructor */
	public RemoteRoomUser() {
	}

	/** full constructor */
	public RemoteRoomUser(String userid, String username, String cardid,
			String userIdentity) {
		this.userid = userid;
		this.username = username;
		this.cardid = cardid;
		this.userIdentity = userIdentity;
	}

	public RemoteRoomUser(int id, String userid, String username,
			String cardid, String userIdentity) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.cardid = cardid;
		this.userIdentity = userIdentity;
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

	@Column(name = "cardid", length = 100)
	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	@Column(name = "user_identity", length = 100)
	public String getUserIdentity() {
		return this.userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

}