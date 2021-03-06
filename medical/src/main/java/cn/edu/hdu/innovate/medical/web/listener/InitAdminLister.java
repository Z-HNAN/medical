package cn.edu.hdu.innovate.medical.web.listener;


import cn.edu.hdu.innovate.medical.domain.Logininfo;
import cn.edu.hdu.innovate.medical.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 用于初始化Admin的监听对象
 * @author Z_HNAN
 *
 */
@Component
public class InitAdminLister implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${init.admin.username}")
	private String ADMIN_USERNAME;

	@Value("${init.admin.password}")
	private String ADMIN_PASSWORD;
	
	@Value("${init.medical.username}")
	private String MEDICAL_USERNAME;
	
	@Value("${init.medical.password}")
	private String MEDICAL_PASSWORD;

	@Autowired
	private ILogininfoService logininfoService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.initAdmin();
	}

	/**
	 * 判断并初始化一个 超级管理员账户以及总公司账号
	 */
	private void initAdmin() {
		// 检测当前管理员的数量
		int countAdmin = this.logininfoService.getCountByUserType(Logininfo.USER_TYPE_ADMIN);
		if (countAdmin == 0) {
			// 当前不存在超级管理员 需要创建一名
			Logininfo admin = new Logininfo();
			admin.setUsername(ADMIN_USERNAME);
			admin.setPassword(ADMIN_PASSWORD);
			admin.setState(Logininfo.STATE_NORMAL);
			admin.setUserType(Logininfo.USER_TYPE_ADMIN);
			this.logininfoService.save(admin);
			
		}
		
		int countMedical = this.logininfoService.getCountByUserType(Logininfo.USER_TYPE_MEDICAL_COM);
		if(countMedical == 0) {
			// 当前不存在卫生所管理员 需要创建一名卫生所管理员
			Logininfo medical = new Logininfo();
			medical.setUsername(MEDICAL_USERNAME);
			medical.setPassword(MEDICAL_PASSWORD);
			medical.setState(Logininfo.STATE_NORMAL);
			medical.setUserType(Logininfo.USER_TYPE_MEDICAL_COM);
			this.logininfoService.save(medical);
			
		}
		
	}
	
}
