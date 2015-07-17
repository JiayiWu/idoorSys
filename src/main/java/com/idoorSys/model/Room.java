package com.idoorSys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 受管理的房间信息
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {
	private int id;

	private String name;

	private String type;

	private String num;

	private Timestamp timetag;

	public Room() {
	}

	public Room(int id) {
		this.id = id;
	}

	public Room(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Room(int id, String name, String type) {
		super();
		this.name = name;
		this.type = type;
		this.id = id;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(unique = true)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Timestamp getTimetag() {
		return timetag;
	}

	public void setTimetag(Timestamp timetag) {
		this.timetag = timetag;
	}

	@PrePersist @PreUpdate // annotation not work :(
	public void changeTimeStamp() {
		setTimetag(new Timestamp(System.currentTimeMillis()));
	}
}
