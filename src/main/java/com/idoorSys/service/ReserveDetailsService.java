package com.idoorSys.service;

import java.util.List;

import com.idoorSys.model.RemoteRoomUser;
import com.idoorSys.model.ReserveDetails;
import com.idoorSys.utils.Msg;

public class ReserveDetailsService extends BaseService {
	private Class<?> className = ReserveDetails.class;

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(className);
	}

	@Override
	public void preAdd() {
		// TODO Auto-generated method stub
	}

	@Override
	public Msg deleteById(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().deleteById(className, (int) id);
	}

	@Override
	public Object getbyId(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().findById(className, (int) id);
	}

}
