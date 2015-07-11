package com.idoorSys.service;

import java.util.List;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.utils.Msg;

public abstract class BaseService implements IdoorService{

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Msg update(Object object) {
		return getBaseDao().update(object);
	}

	@Override
	public Msg add(Object object) {
		return getBaseDao().save(object);
	}

	@Override
	public List<?> findByExample(Object object) {
		return getBaseDao().findByExample(object);
	}
	
	

}
