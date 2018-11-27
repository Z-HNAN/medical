package cn.edu.hdu.zhn.medical.mgrsite.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 处理登录判断对的controller
 * @author Z_HNAN
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.hdu.zhn.medical.base.domain.Logininfo;
import cn.edu.hdu.zhn.medical.base.service.ILogininfoService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;
import cn.edu.hdu.zhn.medical.mgrsite.anno.RequiredLogin;

/**
 * 登录相关的处理器
 * @author Z_HNAN
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 处理管理员的登录的
	 * @return
	 */
	@RequestMapping("adminLogin")
	@ResponseBody
	public JSONResult adminLogin(String username, String password, HttpServletRequest request) {
		JSONResult json = new JSONResult();
		int userType = Logininfo.USER_TYPE_ADMIN;
		try {
			this.logininfoService.login(username, password, userType, request);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 进入管理员首页
	 * @return
	 */
	@RequestMapping("adminMain")
	@RequiredLogin
	public String adminMain() {
		return "admin/main";
	}
	
	/**
	 * 处理卫生所的登录的
	 * @return
	 */
	@RequestMapping("comLogin")
	@ResponseBody
	public JSONResult comLogin(String username, String password, HttpServletRequest request) {
		JSONResult json = new JSONResult();
		int userType = Logininfo.USER_TYPE_MEDICAL_COM;
		try {
			this.logininfoService.login(username, password, userType, request);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 进入卫生所首页
	 * @return
	 */
	@RequestMapping("comMain")
	@RequiredLogin
	public String comMain() {
		return "com/main";
	}
	
	/**
	 * 处理医务室的登录的
	 * @return
	 */
	@RequestMapping("deptLogin")
	@ResponseBody
	public JSONResult deptLogin(String username, String password, HttpServletRequest request) {
		JSONResult json = new JSONResult();
		int userType = Logininfo.USER_TYPE_MEDICAL_ROOM;
		try {
			this.logininfoService.login(username, password, userType, request);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 进入医务室首页
	 * @return
	 */
	@RequestMapping("deptMain")
	@RequiredLogin
	public String deptMain() {
		return "dept/main";
	}
}
