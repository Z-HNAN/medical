package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

public interface LogininfoMapper {
	int insert(Logininfo record);

	Logininfo selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Logininfo record);
	
	/**
	 * 根据用户类型 得到用户的数量
	 * @param userType
	 * @return
	 */
	int getCountByUserType(int userType);

	/**
	 * 判断用户登录的方法
	 * @param username
	 * @param password
	 * @return
	 */
//	Logininfo login(@Param("username") String username, @Param("password") String password, @Param("userType") int userType);
	Logininfo login(@Param("username") String username, @Param("password") String password);
}