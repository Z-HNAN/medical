package cn.edu.hdu.innovate.medical.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录模型
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class Logininfo extends BaseDomain{
	
	public static final int STATE_NORMAL = 0; // 状态正常
	public static final int STATE_LOCK = 1; 	// 锁定用户
	
	public static final int USER_TYPE_EMPLOYEE = 0;	// 员工类型
	public static final int USER_TYPE_MEDICAL_ROOM = 1;	// 医务室类型（公司分部）
	public static final int USER_TYPE_MEDICAL_COM = 2;	// 卫生所类型（总公司）
	public static final int USER_TYPE_ADMIN = 3;	// 管理员类型（管理员）
	
	
	private String username;	// 用户登录用户名
	private String password;	// 用户密码
	private int state;			// 当前用户状态
	private int userType;		// 用户类型
}
