package com.idoorSys.dao;

import com.idoorSys.model.PeriodicPermission;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unchecked")
public class PeriodicPermissionDao extends BaseDao {
    public PeriodicPermission getbyId(int id) {
        return  (PeriodicPermission)super.findById(PeriodicPermission.class, id);
    }
}
