package cn.edu.hdu.innovate.medical.web.interceptor;


import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 负责登录检查的拦截器
 * @author Maibenben
 *
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 登录前的检查
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod) {
			// 对方法做拦截
			HandlerMethod hm = (HandlerMethod) handler;
			RequiredLogin rl = hm.getMethodAnnotation(RequiredLogin.class);
			if(rl != null && UserContext.getCurrent() == null) {
				// 目前尚未用户进行登录 做跳转login界面
				response.sendRedirect("/login.html");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	
	
}
