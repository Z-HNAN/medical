package cn.edu.hdu.zhn.medical.base.service;

import cn.edu.hdu.zhn.medical.base.domain.Employee;

/**
 * 员工信息相关服务
 * @author Z_HNAN
 *
 */
public interface IEmployeeService {

	/**
	 * 通过id查询员工对象
	 * @param id
	 * @return
	 */
	Employee getById(Long id);

	/**
	 * 插入一条记录
	 * @param employee
	 * @param dept
	 */
	void save(Employee employee, Long dept);

	/**
	 * 根据员工jobID或是realname查询
	 * @param jobId
	 * @param realname
	 * @return
	 */
	Long getIdByJobIdOrRealname(String jobId, String realname);
}