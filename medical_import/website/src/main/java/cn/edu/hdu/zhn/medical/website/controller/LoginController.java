package cn.edu.hdu.zhn.medical.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 用户登录的Controller
 * @author Z_HNAN
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.hdu.zhn.medical.base.domain.Logininfo;
import cn.edu.hdu.zhn.medical.base.service.ILogininfoService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;

/**
 * 登录相关的控制器
 * @author Z_HNAN
 *
 */
@Controller
public class LoginController {

	@Autowired
	private ILogininfoService logininfoService;

	@RequestMapping("/login")
	@ResponseBody
	public JSONResult login(String username, String password, HttpServletRequest request) {
		JSONResult json = new JSONResult();
		try {
			int userType = Logininfo.USER_TYPE_EMPLOYEE;
			this.logininfoService.login(username, password, userType, request);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
