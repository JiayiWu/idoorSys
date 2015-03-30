package com.idoorSys.service;

import java.util.List;

import com.idoorSys.model.RemoteRoomUser;
import com.idoorSys.utils.Msg;

public class RemoteRoomUserService extends BaseService {

	private Class<?> className = RemoteRoomUser.class;

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return getBaseDao().getAll(className);
	}

	@Override
	public void preAdd() {
		// TODO Auto-generated method stub
		getBaseDao().save(
				new RemoteRoomUser("1230345", "asdasd", "gbhfgh", "100456456"));
		getBaseDao().save(
				new RemoteRoomUser("gfhf", "nghnaf", "45678", "234234234"));
		getBaseDao().save(
				new RemoteRoomUser("h56456", "mh78i", "4532", "dfh45ty345"));
		getBaseDao()
				.save(new RemoteRoomUser("jhj678", "ghjghj", "78987",
						"56756ufg5jh4"));
	}

	@Override
	public Msg deleteById(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().deleteById(className, (int)id);
	}

	@Override
	public Object getbyId(long id) {
		// TODO Auto-generated method stub
		return getBaseDao().findById(className, (int)id);
	}

}
