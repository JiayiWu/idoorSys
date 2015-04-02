package com.idoorSys.service;

import java.util.List;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.idoorSys.model.SysUser;
import com.idoorSys.utils.Msg;

public class SysUserService extends BaseService {

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void preAdd() {
		// TODO Auto-generated method stub
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		
		SysUser admin = new SysUser();
		String account = "eelab";
		String password = "1234qwer";
		String md5Password = md5PasswordEncoder.encodePassword(password,
				account);
		admin.setAccount(account);
		admin.setPassword(md5Password);
		admin.setRole("admin");
		
		SysUser guard = new SysUser();
		String accountOfGuard = "guard";
		String passwordOfGuard = "guard";
		String md5PasswordOfGuard = md5PasswordEncoder.encodePassword(passwordOfGuard, accountOfGuard);
		guard.setAccount(accountOfGuard);
		guard.setPassword(md5PasswordOfGuard);
		guard.setRole("normal");
		
		getBaseDao().save(admin);
		getBaseDao().save(guard);
	}

	@Override
	public Msg deleteById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getbyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public SysUser checkExits(String account, String password) {
		List<SysUser> userList = (List<SysUser>) getBaseDao().findByProperty(
				SysUser.class, "account", account);
		SysUser sysUserDB = null;
		if (userList != null && userList.size() > 0) {
			sysUserDB = userList.get(0);
		}
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		if (sysUserDB != null
				&& sysUserDB.getPassword().equals(
						md5PasswordEncoder.encodePassword(password, account))) {
			return sysUserDB;
		}
		return null;
	}
}
