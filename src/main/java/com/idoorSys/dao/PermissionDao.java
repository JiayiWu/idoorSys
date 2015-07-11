package com.idoorSys.dao;

import java.util.List;

import com.idoorSys.model.Permission;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unchecked")
public class PermissionDao extends BaseDao {
    public List<Permission> getAll() {
        return (List<Permission>) super.getAll(Permission.class);
    }
}
