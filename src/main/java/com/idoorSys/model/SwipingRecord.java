package com.idoorSys.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = IDENTITY)
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

}