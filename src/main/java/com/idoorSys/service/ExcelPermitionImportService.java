package com.idoorSys.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.idoorSys.dao.PermissionDao;
import com.idoorSys.dao.PermissionUserDao;
import com.idoorSys.dao.RoomDao;
import com.idoorSys.model.Permission;
import com.idoorSys.model.PermissionUser;
import com.idoorSys.model.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExcelPermitionImportService {

	@Resource
	RoomDao roomDao;
	@Resource
	PermissionUserDao permissionUserDao;
	@Resource
	PermissionDao permissionDao;

	public static String getConvertedID(String id) {
		String id1 = id.substring(0, id.length() - 5);
		int id1Num = Integer.parseInt(id1);
		String id2 = id.substring(id.length() - 5, id.length());
		int id2Num = Integer.parseInt(id2);
		return String.format("%02X%04X", id1Num, id2Num);
	}

	public void importExcel(String path) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			int rows = sheet.getPhysicalNumberOfRows();
			List<Permission> permissions = new ArrayList<>();

			HashMap<String, Room> rooms = new HashMap<>();
			HashMap<String, PermissionUser> pusers = new HashMap<>();

			for (int i = 1; i < rows; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					String roomName = row.getCell(0).getStringCellValue();
					String userName = row.getCell(1).getStringCellValue();
					String cardNum = getConvertedID((int) row.getCell(2)
							.getNumericCellValue() + "");

					Room room = new Room();
					room.setName(roomName);
					if (!rooms.containsKey(roomName))
						rooms.put(roomName, room);

					PermissionUser permissionUser = new PermissionUser();
					permissionUser.setName(userName);
					permissionUser.setCard_num(cardNum);
					if (!pusers.containsKey(cardNum))
						pusers.put(cardNum, permissionUser);

					Permission permission = new Permission();
					permission.setPermission_user(pusers.get(cardNum));
					permission.setRoom(rooms.get(roomName));
					permission.setType("always");

					permissions.add(permission);
				}
			}
			Set<String> keySets = rooms.keySet();
			for (String key : keySets) {
				Room room = rooms.get(key);
				roomDao.save(room);
			}
			Set<String> keySets2 = pusers.keySet();
			for (String key : keySets2) {
				PermissionUser puser = pusers.get(key);
				roomDao.save(puser);
			}
			for (Permission permission : permissions) {
				permissionDao.save(permission);
			}

		} catch (Exception e) {
			System.out.println(e);

		}

	}

}
