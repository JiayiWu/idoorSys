package com.idoorSys.dao;

import com.idoorSys.model.UGroup;

import java.util.List;

/**
 * Created by Ezio on 4/14/2015.
 */
public class UGroupDao extends BaseDao {
    public List<UGroup> getAll() {
        return (List<UGroup>)super.getAll(UGroup.class);
    }
}
