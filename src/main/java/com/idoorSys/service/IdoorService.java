package com.idoorSys.service;

import java.util.List;

import com.idoorSys.utils.Msg;

public interface IdoorService {

	public abstract List<?> getAll();

	public abstract Msg deleteById(int id);

	public abstract Msg update(Object room);

	public abstract Msg add(Object room);

	public abstract Object getbyId(int id);
	
	public abstract List<?> findByExample(Object object);

}