package cn.edu.hdu.zhn.medical.base.service.impl;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.hdu.zhn.medical.base.domain.Logininfo;
import cn.edu.hdu.zhn.medical.base.mapper.LogininfoMapper;
import cn.edu.hdu.zhn.medical.base.service.ILogininfoService;
import cn.edu.hdu.zhn.medical.base.util.UserContext;

@Service
@Transactional
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
	private LogininfoMapper logininfoMapper;

	@Override
	public void login(String username, String password, int userType, HttpServletRequest request) {
		Logininfo logininfo = this.logininfoMapper.login(username, password, userType);
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
