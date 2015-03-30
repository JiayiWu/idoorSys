package com.idoorSys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class PermissionUser implements Serializable {

	private long id;

	private String stdNum;

	private String cardNum;

	private String name;

	private String type;

	private String phone;

	private String group;

	public PermissionUser() {
		super();
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}

	public PermissionUser(String cardNum) {
		super();
		this.cardNum = cardNum;
	}

	public PermissionUser(long id, String cardNum, String name, String type,
			String stdNum) {
		super();
		this.id = id;
		this.cardNum = cardNum;
		this.name = name;
		this.type = type;
		this.stdNum = stdNum;
	}

	public PermissionUser(String cardNum, String name, String type,
			String stdNum) {
		super();
		this.cardNum = cardNum;
		this.name = name;
		this.type = type;
		this.stdNum = stdNum;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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

	@Column
	public String getStdNum() {
		return stdNum;
	}

	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}

	@Column
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ugroup")
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
