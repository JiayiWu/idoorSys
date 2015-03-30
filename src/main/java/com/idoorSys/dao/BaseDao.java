package com.idoorSys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idoorSys.model.Permission;
import com.idoorSys.model.Room;
import com.idoorSys.utils.Msg;

public class BaseDao {
	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	private SessionFactory sessionFactory;

	private Session session;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public Msg save(Object entity) {
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().save(entity);
			tx.commit();
			getSession().clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeSession();
			return Msg.FAIL;
		}
	}

	private void closeSession() {
		// TODO Auto-generated method stub
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public Msg update(Object entity) {
		try {
			getSession().beginTransaction();
			getSession().update(entity);
			getSession().getTransaction().commit();
			getSession().clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
			return Msg.FAIL;
		}

	}

	public Msg delete(Object entity) {
		try {
			getSession().beginTransaction();
			getSession().delete(entity);
			getSession().getTransaction().commit();
			getSession().clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
			return Msg.FAIL;
		}
	}

	public Msg deleteById(Class<?> className, long id) {
		try {
			getSession().beginTransaction();
			Object instance = getSession().get(className, id);
			getSession().delete(instance);
			getSession().getTransaction().commit();
			getSession().clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
			return Msg.FAIL;
		}
	}

	public Msg deleteById(Class<?> className, int id) {
		try {
			getSession().beginTransaction();
			Object instance = getSession().get(className, id);
			getSession().delete(instance);
			getSession().getTransaction().commit();
			getSession().clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
			return Msg.FAIL;
		}
	}

	public List<?> getAll(Class<?> className) {
		List<?> list = null;
		try {
			getSession().beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			list = criteria.list();
			getSession().getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
		}
		return list;
	}

	public Object findById(Class<?> className, Long id) {
		try {
			Object instance = getSession().get(className, id);
			closeSession();
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			closeSession();
			throw re;
		}
	}

	public Object findById(Class<?> className, int id) {
		try {
			Object instance = getSession().get(className, id);
			closeSession();
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			closeSession();
			throw re;
		}
	}

	public List<?> findByExample(Object entity) {
		List<?> list = null;
		try {
			getSession().beginTransaction();
			Criteria criteria = session.createCriteria(entity.getClass());
			criteria.addOrder(Order.desc("id"));
			criteria.add(Example.create(entity).enableLike(MatchMode.ANYWHERE));
			list = criteria.list();
			getSession().getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			getSession().clear();
		} catch (Exception e) {
			e.printStackTrace();
			closeSession();
		}
		return list;
	}

	public List<?> findByProperty(Class<?> className, String propertyName,
			Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from " + className.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<?> list = queryObject.list();
			closeSession();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			closeSession();
			throw re;

		}
	}

	public List<Object[]> execSqlQuery(String sql) {
		// TODO Auto-generated method stub
		try {
			Transaction tx = getSession().beginTransaction();
			List<Object[]> objects = getSession().createSQLQuery(sql).list();
			tx.commit();
			closeSession();
			return objects;
		} catch (Exception re) {
			re.printStackTrace();
			closeSession();
			throw re;
		}
	}
	
	public int clearTable(String myTable){
	    String hql = String.format("delete from %s",myTable);
	    Query query = getSession().createQuery(hql);
	    return query.executeUpdate();
	}

}
