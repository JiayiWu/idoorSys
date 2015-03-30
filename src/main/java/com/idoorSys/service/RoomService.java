package com.idoorSys.service;

import java.util.List;

import com.idoorSys.dao.RoomDao;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;

public class RoomService extends BaseService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getAll()
	 */
	@Override
	public List<?> getAll() {
		return ((RoomDao) getBaseDao()).getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#preAdd()
	 */
	@Override
	public void preAdd() {
		getBaseDao().save(new Room("fas", "411"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#deleteById(long)
	 */
	@Override
	public Msg deleteById(long id) {
		return getBaseDao().deleteById(Room.class, id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idoorSys.service.IdoorService#getbyId(long)
	 */
	@Override
	public Object getbyId(long id) {
		return getBaseDao().findById(Room.class, id);
	}

}
