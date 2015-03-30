package com.idoorSys.service;

import java.util.List;

import com.idoorSys.model.PermissionUser;
import com.idoorSys.utils.Msg;

public class PermissionUserService extends BaseService {

	private Class<?> className = PermissionUser.class;

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(className);
	}

	@Override
	public void preAdd() {
		// TODO Auto-generated method stub
		// getBaseDao().save(new
		// PermissionUser("1230345","擦","gbhfgh","100456456"));

	}

	@Override
	public Msg deleteById(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().deleteById(className, id);
	}

	@Override
	public Object getbyId(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().findById(className, id);
	}

	public List<PermissionUser> getByGroup(String group) {
		// TODO Auto-generated method stub
		return (List<PermissionUser>) getBaseDao().findByProperty(className, "group", group);
	}

}
