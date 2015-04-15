package com.idoorSys.service;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.UGroupDao;
import com.idoorSys.model.UGroup;
import com.idoorSys.utils.Msg;

import java.util.List;

/**
 * Created by Ezio on 4/14/2015.
 */
public class UGroupService extends BaseService {
    private Class<?> className = UGroup.class;
    public List<UGroup> getAll() {
        return (List<UGroup>)getBaseDao().getAll(className);
    }

    @Override
    public void preAdd() {
        BaseDao dao = getBaseDao();
        dao.save(new UGroup("教师"));
        dao.save(new UGroup("学生"));
        dao.save(new UGroup("工作人员"));
    }

    @Override
    public Msg deleteById(long id) {
        return getBaseDao().deleteById(UGroup.class, id);
    }

    @Override
    public Object getbyId(long id) {
        return null;
    }

    public Msg deleteByName(String group) {
        List<?> example = getBaseDao().findByExample(new UGroup(group));
        if (example.size()==0) { // group not found
            return Msg.FAIL;
        }
        Msg msg = getBaseDao().delete(example.get(0));
        return msg;
    }
}
