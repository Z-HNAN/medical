package cn.edu.hdu.innovate.medical.util;

import cn.edu.hdu.innovate.medical.domain.Logininfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


public class UserContext {

	private static final String USER_IN_SESSION = "logininfo";

	/**
	 * 反向获取httpSession的方法
	 * @return HttpSession
	 */
	private static HttpSession getSession() {
		return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	}

	/**
	 * 存放current到session中
	 * @param current
	 */
	public static void putCurrent(Logininfo current) {
		getSession().setAttribute(USER_IN_SESSION, current);
	}

	/**
	 * 从session中获取当前登录对象
	 * @return
	 */
	public static Logininfo getCurrent() {
		return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
	}


}
