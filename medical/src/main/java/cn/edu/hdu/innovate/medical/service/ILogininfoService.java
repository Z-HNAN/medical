package cn.edu.hdu.innovate.medical.service;


import cn.edu.hdu.innovate.medical.domain.Logininfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录相关的服务
 * @author Z_HNAN
 *
 */
public interface ILogininfoService {

	/**
	 * 用户登录的方法
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
//	void login(String username, String password, int userType, HttpServletRequest request);
	void login(String username, String password, HttpServletRequest request);
	/**
	 * 根据用户类型 返回当前的用户数量
	 * @param userType 用户类型
	 * @return  用户的数量
	 */
	int getCountByUserType(int userType);
	
	/**
	 * 存储一个账户的方法
	 * @param logininfo
	 */
	void save(Logininfo logininfo);
}
