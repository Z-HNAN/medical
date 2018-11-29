package cn.edu.hdu.innovate.medical.service;

import cn.edu.hdu.innovate.medical.domain.MedicalRoom;

import java.math.BigDecimal;
import java.util.List;

/**
 * 医务室服务类
 * @author Z_HNAN
 *
 */
public interface IMedicalRoomService {

	/**
	 * 创建医务室的方法
	 * @param username
	 * @param password
	 * @param comId
	 * @param budgetAmount
	 */
	void createMedicalRoom(String username, String password, Long comId, BigDecimal budgetAmount);

	/**
	 * 得到medicalRoom
	 * @param medicalDept
	 * @return
	 */
	MedicalRoom get(Long medicalDept);

	/**
	 * 查询所有的医务室
	 * @return
	 */
	List<MedicalRoom> listAll();

	/**
	 * 通过负责人id得到此医务室信息
	 * @param id
	 * @return
	 */
	MedicalRoom getByPrincipalId(Long id);

	/**
	 * 更新医务室账户金额
	 * @param medicalRoom
	 */
	void updateAmount(MedicalRoom medicalRoom);

	/**
	 * 后台卫生所放款的功能
	 * @param medicalDept
	 * @param currentAmount
	 */
	void passMoney(Long medicalDept, BigDecimal currentAmount);

}
