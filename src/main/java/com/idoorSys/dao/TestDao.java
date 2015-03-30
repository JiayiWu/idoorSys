package com.idoorSys.dao;

import com.idoorSys.model.IdoorTest;

public class TestDao extends BaseDao {

	public IdoorTest getTest() {
		save(new IdoorTest("hello2"));
		IdoorTest it = (IdoorTest) (getAll(IdoorTest.class).get(0));
		return it;
	}
}
