package com.idoorSys.dao;

import com.idoorSys.model.RemoteController;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ezio on 7/16/2015.
 */
@Repository
public class RemoteControllerDao extends BaseDao {
    public List<String> getIpList() {
        Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(RemoteController.class);
            List<RemoteController> list = criteria.list();
            List<String> ips = new ArrayList<>(list.size());
            for (RemoteController rc: list) {
                ips.add(rc.getIp());
            }
            return ips;
        }
        finally {
            session.close();
        }
    }
}
