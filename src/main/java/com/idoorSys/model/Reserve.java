package com.idoorSys.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 预约权限信息
 */
@Entity
@Table(name = "reserve")
public class Reserve implements Serializable {

	private int id;
	private String room_num;
	private Date end_time;
	private Date begin_time;
	private String seat_id;
	private String card_num;
	private String old_id;
	private Timestamp timetag;

	public Timestamp getTimetag() {
		return timetag;
	}

	public void setTimetag(Timestamp timetag) {
		this.timetag = timetag;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getRoom_num() {
		return room_num;
	}

	public void setRoom_num(String lid) {
		this.room_num = lid;
	}

	@Column
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date endTime) {
		this.end_time = endTime;
	}

	@Column
	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date beginTime) {
		this.begin_time = beginTime;
	}

	@Column
	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String cardNum) {
		this.card_num = cardNum;
	}

	@Column
	public String getOld_id() {
		return old_id;
	}

	public void setOld_id(String oldId) {
		this.old_id = oldId;
	}

	@Column
	public String getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(String seatId) {
		this.seat_id = seatId;
	}

	@Override
	public String toString() {
		return "Reserve [lid=" + room_num + ", endTime=" + end_time + ", beginTime="
				+ begin_time + ", seatId=" + seat_id + ", cardNum=" + card_num
				+ ", oldId=" + old_id + "]";
	}
	
	

}
