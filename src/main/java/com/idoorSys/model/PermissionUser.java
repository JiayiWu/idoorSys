package com.idoorSys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 刷卡用户信息
 */
@Entity
@Table(name = "permission_user")
public class PermissionUser implements Serializable {

	private int id;

	private String std_num;

	private String card_num;

	private String name;

	private String type;

	private String phone;

	private String group;

	public PermissionUser() {
		super();
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public PermissionUser(String card_num) {
		super();
		this.card_num = card_num;
	}

	public PermissionUser(int id, String card_num, String name, String type,
			String std_num) {
		super();
		this.id = id;
		this.card_num = card_num;
		this.name = name;
		this.type = type;
		this.std_num = std_num;
	}

	public PermissionUser(String card_num, String name, String type,
			String std_num) {
		super();
		this.card_num = card_num;
		this.name = name;
		this.type = type;
		this.std_num = std_num;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String cardNum) {
		this.card_num = cardNum;
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
	public String getStd_num() {
		return std_num;
	}

	public void setStd_num(String stdNum) {
		this.std_num = stdNum;
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
