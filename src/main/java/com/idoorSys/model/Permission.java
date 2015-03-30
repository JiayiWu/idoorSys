package com.idoorSys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"permissionUser_cardNum", "room_id" }))
public class Permission implements Serializable {

	private long id;
	private Room room;
	private PermissionUser permissionUser;
	private String type;

	public Permission() {

	}

	public Permission(Room room, PermissionUser permissionUser, String type) {
		super();
		this.room = room;
		this.permissionUser = permissionUser;
		this.type = type;
	}

	public Permission(long id, Room room, PermissionUser permissionUser,
			String type) {
		super();
		this.id = id;
		this.room = room;
		this.permissionUser = permissionUser;
		this.type = type;

	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName = "cardNum")
	public PermissionUser getPermissionUser() {
		return permissionUser;
	}

	public void setPermissionUser(PermissionUser permissionUser) {
		this.permissionUser = permissionUser;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
