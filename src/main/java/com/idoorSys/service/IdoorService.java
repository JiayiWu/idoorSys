package com.idoorSys.service;

import java.util.List;

import com.idoorSys.utils.Msg;

public interface IdoorService {

	public abstract List<?> getAll();

	public abstract void preAdd();

	public abstract Msg deleteById(long id);

	public abstract Msg update(Object room);

	public abstract Msg add(Object room);

	public abstract Object getbyId(long id);
	
	public abstract List<?> findByExample(Object object);

}