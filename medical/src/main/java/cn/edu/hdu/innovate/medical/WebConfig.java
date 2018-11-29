package cn.edu.hdu.innovate.medical;

import cn.edu.hdu.innovate.medical.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 设置欢迎页 为 login.html  即设置了 / -> login.html
		registry.addViewController("/").setViewName("login.html");
		WebMvcConfigurer.super.addViewControllers(registry);

		// 设置后台欢迎页 为 login.html  即设置了 / -> login.html
		registry.addViewController("/admin").setViewName("loginAdmin.html");
		WebMvcConfigurer.super.addViewControllers(registry);
	}

}
