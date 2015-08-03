package com.idoorSys.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 刷卡用户权限信息
 */
@Entity
@Table(name = "permission",uniqueConstraints = @UniqueConstraint(columnNames = {"card_num", "room_id" }))
public class Permission implements Serializable {

	private int id;
	private Room room;
	private PermissionUser permission_user;
	// 用户权限类型0:super	1:always	2:periodic	3:reserve
	private String type;
	private Timestamp timetag;

	public Timestamp getTimetag() {
		return timetag;
	}

	public void setTimetag(Timestamp timetag) {
		this.timetag = timetag;
	}

	public Permission() {
	}

	public Permission(Room room, PermissionUser permission_user, String type) {
		super();
		this.room = room;
		this.permission_user = permission_user;
		this.type = type;
	}

	public Permission(int id, Room room, PermissionUser permission_user,
			String type) {
		super();
		this.id = id;
		this.room = room;
		this.permission_user = permission_user;
		this.type = type;

	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	@JoinColumn(name = "card_num", referencedColumnName = "card_num")
	public PermissionUser getPermission_user() {
		return permission_user;
	}

	public void setPermission_user(PermissionUser permissionUser) {
		this.permission_user = permissionUser;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
