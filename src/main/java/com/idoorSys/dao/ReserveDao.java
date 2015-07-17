package com.idoorSys.dao;

import java.sql.Timestamp;
import java.util.List;

import com.idoorSys.model.Reserve;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("unchecked")
public class ReserveDao extends BaseDao {
    public Msg save(Reserve entity) {
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

    public Msg update(Reserve entity) {
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


    public List<Reserve> getAll() {
        return (List<Reserve>) super.getAll(Reserve.class);
    }
}
