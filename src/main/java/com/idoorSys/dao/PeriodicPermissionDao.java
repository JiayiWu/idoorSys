package com.idoorSys.dao;

import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.utils.Msg;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository @SuppressWarnings("unchecked")
public class PeriodicPermissionDao extends BaseDao {
    public Msg save(PeriodicPermission entity) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            entity.setTimetag(new Timestamp(System.currentTimeMillis()));
            session.save(entity);
            tx.commit();
            session.clear();
            return Msg.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return Msg.FAIL;
        } finally {
            session.close();
        }
    }

    public Msg update(PeriodicPermission entity) {
        Session session = getSession();
        try {
            entity.setTimetag(new Timestamp(System.currentTimeMillis()));
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.clear();
            return Msg.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            if (session!=null) {
                session.getTransaction().rollback();
            }
            return Msg.FAIL;
        } finally {
            session.close();
        }

    }


    public PeriodicPermission getbyId(int id) {
        return  (PeriodicPermission)super.findById(PeriodicPermission.class, id);
    }
}
