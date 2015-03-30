package com.idoorSys.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.idoorSys.model.Device;

public class EndPointDevicesDao {

	public List<Device> getAll() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = (SessionFactory) new LocalSessionFactoryBean();
		return null;
	}

}
