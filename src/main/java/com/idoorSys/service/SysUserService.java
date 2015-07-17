package com.idoorSys.service;

import com.idoorSys.dao.SysUserDao;
import com.idoorSys.model.SysUser;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理登陆用户信息
 */
@Service
public class SysUserService {
	@Resource
	private SysUserDao dao;

	public void preAdd() {
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
		
		dao.save(admin);
		dao.save(guard);
	}

	public SysUser checkExits(String account, String password) {
		List<SysUser> userList = (List<SysUser>) dao.findByProperty(
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
