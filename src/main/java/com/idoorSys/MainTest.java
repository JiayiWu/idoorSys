package com.idoorSys;

import com.idoorSys.model.Permission;
import com.idoorSys.model.Room;
import com.idoorSys.service.PermissionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		BaseDao dao = ((BaseDao) context.getBean("periodicPermissionDao"));
//		System.out.println(dao.getSessionFactory().getAllClassMetadata());
//		List<PermissionUser> all = (List<PermissionUser>) dao.getAll(PermissionUser.class);
//		System.out.println(all);


//		PermissionService service = ((PermissionService) context.getBean("permissionService"));
//		List<Permission> all = (List<Permission>) service.getPageAll(20,40);
//		for (Permission room : all) {
//			System.out.println(room.getPermissionUser().getName());
//		}
//		System.out.println(all.size());
	}

}
