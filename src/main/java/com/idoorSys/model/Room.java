package com.idoorSys.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type")
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

	public void setNum(String nameEn) {
		this.num = nameEn;
	}

}
