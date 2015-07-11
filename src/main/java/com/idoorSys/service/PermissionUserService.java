package com.idoorSys.service;

import java.util.List;

import com.idoorSys.dao.PermissionUserDao;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务，提供对用户数据的操作
 */
@Service
public class PermissionUserService {
	@Resource
	private PermissionUserDao dao;

	public List<PermissionUser> getAll() {
		return dao.getAll();
	}
	public List<PermissionUser> getPageAll(int up, int size) {
		return dao.getPageAll(up, size);
	}

	public Msg update(Object object) {
		return dao.update(object);
	}
	public Msg add(Object object) {
		return dao.save(object);
	}


	public Msg deleteById(int id) {
		return dao.deleteById(PermissionUser.class, id);
	}

	public PermissionUser getById(int id) {
		return (PermissionUser)dao.findById(PermissionUser.class, id);
	}

	public List<PermissionUser> getByGroup(String group) {
		return dao.getByGroup(group);
	}

	public List<PermissionUser> findByExample(Object object) {
		return dao.findByExample(object);
	}
}
