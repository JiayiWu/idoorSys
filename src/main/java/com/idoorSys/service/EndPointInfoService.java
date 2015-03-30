package com.idoorSys.service;

import java.util.List;

import com.idoorSys.dao.EndPointPermissionDao;
import com.idoorSys.dao.EndPointUserDao;
import com.idoorSys.model.Device;
import com.idoorSys.model.EndPointTempPermissionDao;
import com.idoorSys.model.TempPermmtion;

public class EndPointInfoService {

	public boolean uploadPermsisionInfo() {
		PermissionService permissionServic = null;
		List<?> p = permissionServic.getAll();
		EndPointPermissionDao endPermissionDao = null;
		if (saveUsers(p) && endPermissionDao.save(p)) {
			return true;
		}
		return false;
	}

	private boolean saveUsers(List<?> p) {
		EndPointUserDao epud = null;
		return epud.saveAll(p);
		// TODO Auto-generated method stub
	}

	public List<Device> getDevices() {
		EndPointDevicesDao epd = null;
		return epd.getAll();
	}

	public boolean uploadTempPermissions() {
		List<TempPermmtion> tempPermmtions = getTodayTempPermissions();
		EndPointTempPermissionDao eptpd = null;
		return eptpd.saveAll(eptpd);
	}

	private List<TempPermmtion> getTodayTempPermissions() {
		// TODO Auto-generated method stub
		return null;
	}
}
