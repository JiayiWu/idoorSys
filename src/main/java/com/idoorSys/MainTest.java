package com.idoorSys;

import com.idoorSys.dao.BaseDao;
import com.idoorSys.dao.PeriodicPermissionDao;
import com.idoorSys.dao.PermissionDao;
import com.idoorSys.model.PeriodicPermission;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import com.idoorSys.service.PeriodicPermissionService;
import com.idoorSys.utils.LocalIpAddressService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Time;
import java.util.List;

public class MainTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BaseDao dao = ((BaseDao) context.getBean("periodicPermissionDao"));
		PermissionUser user = (PermissionUser) dao.getAll(PermissionUser.class).get(0);

		PeriodicPermission permission = new PeriodicPermission();
		Room room = (Room)dao.getAll(Room.class).get(1);
		permission.setPermissionUser(user);
		permission.setRoom(room);
		permission.setDayOfWeek(1);
		permission.setBeginTime(Time.valueOf("0:0:0"));
		permission.setEndTime(Time.valueOf("23:59:59"));
		dao.save(permission);
//		PeriodicPermissionService service = ((PeriodicPermissionService) context.getBean("periodicPermissionService"));
//		for (PeriodicPermission permission : ((List<PeriodicPermission>) service.getAll())) {
//			System.out.println(permission.getPermissionUser().getCardNum());
//		}
	}

}
