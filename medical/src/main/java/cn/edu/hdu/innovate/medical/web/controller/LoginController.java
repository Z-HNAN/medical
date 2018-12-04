package cn.edu.hdu.innovate.medical.web.controller;


/**
 * 处理登录判断对的controller
 *
 * @author Z_HNAN
 */

import cn.edu.hdu.innovate.medical.domain.Logininfo;
import cn.edu.hdu.innovate.medical.service.ILogininfoService;
import cn.edu.hdu.innovate.medical.util.JSONResult;
import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录相关的处理器
 * @author Z_HNAN
 *
 */
@Controller
public class LoginController {

    private int userType;

	@Autowired
	private ILogininfoService logininfoService;

	@RequestMapping("/login")
	@ResponseBody
	public JSONResult login(String username, String password, HttpServletRequest request) {
		JSONResult json = new JSONResult();
		try {
//			int userType = Logininfo.USER_TYPE_EMPLOYEE;
//			this.logininfoService.login(username, password, userType, request);
            this.logininfoService.login(username, password, request);
            userType = UserContext.getCurrent().getUserType();
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("/loginSuccess")
    @RequiredLogin
    public String loginSuccess(){
        switch (userType){
            case Logininfo.USER_TYPE_EMPLOYEE:
                return "website/personal";
            case Logininfo.USER_TYPE_ADMIN:
                return "mgrsite/admin/main";
            case Logininfo.USER_TYPE_MEDICAL_COM:
                return "mgrsite/com/main";
            case Logininfo.USER_TYPE_MEDICAL_ROOM:
                return "mgrsite/dept/main";
            default:
                throw new RuntimeException("未知用户类型");
        }
    }
	/**
	 * 处理管理员的登录的
	 * @return
	 */
	/*@RequestMapping("adminLogin")
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
	}*/
	
	
	/**
	 * 进入管理员首页
	 * @return
	 */
	/*@RequestMapping("adminMain")
	@RequiredLogin
	public String adminMain() {
		return "mgrsite/admin/main";
	}
	*/
	/**
	 * 处理卫生所的登录的
	 * @return
	 */
	/*@RequestMapping("comLogin")
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
	}*/
	
	/**
	 * 进入卫生所首页
	 * @return
	 */
	/*@RequestMapping("comMain")
	@RequiredLogin
	public String comMain() {
		return "mgrsite/com/main";
	}
	*/
	/**
	 * 处理医务室的登录的
	 * @return
     */
	/*
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
	}*/
	
	/**
	 * 进入医务室首页
	 * @return
	 */
	/*@RequestMapping("deptMain")
	@RequiredLogin
	public String deptMain() {
		return "mgrsite/dept/main";
	}*/
}
