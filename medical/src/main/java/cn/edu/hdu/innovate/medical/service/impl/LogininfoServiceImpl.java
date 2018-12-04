package cn.edu.hdu.innovate.medical.service.impl;


import cn.edu.hdu.innovate.medical.domain.Logininfo;
import cn.edu.hdu.innovate.medical.mapper.LogininfoMapper;
import cn.edu.hdu.innovate.medical.service.ILogininfoService;
import cn.edu.hdu.innovate.medical.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
	private LogininfoMapper logininfoMapper;

	@Override
//	public void login(String username, String password, int userType, HttpServletRequest request) {
//		Logininfo logininfo = this.logininfoMapper.login(username, password, userType);
	public void login(String username, String password, HttpServletRequest request) {
		Logininfo logininfo = this.logininfoMapper.login(username, password);
		if (logininfo == null) {
			throw new RuntimeException("用户名或密码错误");
		}
		UserContext.putCurrent(logininfo);
	}

	@Override
	public int getCountByUserType(int userType) {
		return this.logininfoMapper.getCountByUserType(userType);
	}

	@Override
	public void save(Logininfo logininfo) {
		this.logininfoMapper.insert(logininfo);
	}

}
