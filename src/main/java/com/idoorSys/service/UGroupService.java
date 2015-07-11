package com.idoorSys.service;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.UGroupDao;
import com.idoorSys.model.UGroup;
import com.idoorSys.utils.Msg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理刷卡用户组别信息
 */
@Service
public class UGroupService {
    @Resource
    private UGroupDao dao;

    public List<UGroup> getAll() {
        return (List<UGroup>)dao.getAll(UGroup.class);
    }

    public void preAdd() {
        dao.save(new UGroup("教师"));
        dao.save(new UGroup("学生"));
        dao.save(new UGroup("工作人员"));
    }

    public Msg deleteById(int id) {
        return dao.deleteById(UGroup.class, id);
    }

    public Object getbyId(int id) {
        return null;
    }

    public Msg deleteByName(String group) {
        List<?> example = dao.findByExample(new UGroup(group));
        if (example.size()==0) { // group not found
            return Msg.FAIL;
        }
        return dao.delete(example.get(0));
    }

    public Msg add(UGroup uGroup) {
        return dao.save(uGroup);
    }
}
