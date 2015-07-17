package com.idoorSys;

import com.idoorSys.service.SyncService;
import com.idoorSys.utils.Syncronizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("start");
		SyncService syncService = (SyncService) context.getBean("syncService");
		syncService.syncNow();
		System.out.println("done");
	}

}
