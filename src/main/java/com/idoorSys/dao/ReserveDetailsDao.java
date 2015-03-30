package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.ReserveDetails;

public class ReserveDetailsDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<ReserveDetails> getAll() {
		return (List<ReserveDetails>) super.getAll(ReserveDetails.class);
	}
}
