package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Reserve;
import com.idoorSys.model.Room;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unchecked")
public class ReserveDao extends BaseDao {
    public List<Reserve> getAll() {
        return (List<Reserve>) super.getAll(Reserve.class);
    }
}
