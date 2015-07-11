package com.idoorSys.dao;

import com.idoorSys.model.UGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository @SuppressWarnings("unchecked")
public class UGroupDao extends BaseDao {
    public List<UGroup> getAll() {
        return (List<UGroup>)super.getAll(UGroup.class);
    }
}
