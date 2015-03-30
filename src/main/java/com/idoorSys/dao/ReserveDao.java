package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Reserve;
import com.idoorSys.model.Room;

public class ReserveDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Reserve> getAll() {
		return (List<Reserve>) super.getAll(Reserve.class);
	}
	
}
