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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public Msg save(Object entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(entity);
			tx.commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
			return Msg.FAIL;
		}
	}

	public Msg update(Object entity) {
		Session session = getSession();
		try {
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
			session.close();
			return Msg.FAIL;
		}

	}

	public Msg delete(Object entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			session.close();
			return Msg.FAIL;
		}
	}

	public Msg deleteById(Class<?> className, long id) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Object instance = getSession().get(className, id);
			session.delete(instance);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			session.close();
			return Msg.FAIL;
		}
	}

	public Msg deleteById(Class<?> className, int id) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Object instance = getSession().get(className, id);
			session.delete(instance);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			session.close();
			return Msg.FAIL;
		}
	}

	public List<?> getAll(Class<?> className) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			list = criteria.list();
			session.getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			session.close();
		}
		return list;
	}

	public Object findById(Class<?> className, Long id) {
		Session session = getSession();
		try {
			Object instance = session.get(className, id);
			session.close();
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			session.close();
			throw re;
		}
	}

	public Object findById(Class<?> className, int id) {
		Session session = getSession();
		try {
			Object instance = getSession().get(className, id);
			session.close();
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			session.close();
			throw re;
		}
	}

	public List<?> findByExample(Object entity) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(entity.getClass());
			criteria.addOrder(Order.desc("id"));
			criteria.add(Example.create(entity).enableLike(MatchMode.ANYWHERE));
			list = criteria.list();
			session.getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return list;
	}

	public List<?> findByProperty(Class<?> className, String propertyName,
			Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List<?> list = queryObject.list();
			session.close();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			session.close();
			throw re;

		}
	}

	public List<Object[]> execSqlQuery(String sql) {
		Session session = getSession();
		try {
			session.beginTransaction();
			List<Object[]> objects = session.createSQLQuery(sql).list();
			session.getTransaction().commit();
			session.clear();
			session.close();
			return objects;
		} catch (Exception re) {
			re.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			session.close();
			throw re;
		}
	}
	
	public int clearTable(String myTable){
	    String hql = String.format("delete from %s",myTable);
	    Query query = getSession().createQuery(hql);
	    return query.executeUpdate();
	}

}
