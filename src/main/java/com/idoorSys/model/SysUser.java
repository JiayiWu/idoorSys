package com.idoorSys.model;

import javax.persistence.*;

/**
 * 管理系统用户
 */
@Entity
@Table(name = "sysuser")
public class SysUser {
	private int id;
	private String account;
	private String password;
	private String role;

	// Property accessors
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "pword")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
