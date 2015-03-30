package com.idoorSys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "privilege")
public class Privilege implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String isSuper;
	private String haveReserve;

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(String userid, String isSuper, String haveReserve) {
		this.userid = userid;
		this.isSuper = isSuper;
		this.haveReserve = haveReserve;
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

	@Column(name = "Is_Super", length = 1)
	public String getIsSuper() {
		return this.isSuper;
	}

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}

	@Column(name = "Have_Reserve", length = 1)
	public String getHaveReserve() {
		return this.haveReserve;
	}

	public void setHaveReserve(String haveReserve) {
		this.haveReserve = haveReserve;
	}

}