package com.idoorSys.dao;

import com.idoorSys.model.PermissionUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository @SuppressWarnings("unchecked")
public class PermissionUserDao extends BaseDao {
    public List<PermissionUser> getAll() {
        return (List<PermissionUser>)super.getAll(PermissionUser.class);
    }
    public List<PermissionUser> getPageAll(int up, int size) {
        return (List<PermissionUser>)super.getPageAll(PermissionUser.class, up, size);
    }
    public List<PermissionUser> getByGroup(String group) {
        return (List<PermissionUser>) super.findByProperty(PermissionUser.class, "group", group);
    }
    public List<PermissionUser> findByExample(Object object) {
        return (List<PermissionUser>)super.findByExample(object);
    }
}
