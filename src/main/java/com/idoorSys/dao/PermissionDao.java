package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Permission;

public class PermissionDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Permission> getAll() {
		return (List<Permission>) super.getAll(Permission.class);
	}
}
