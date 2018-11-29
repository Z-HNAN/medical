package cn.edu.hdu.innovate.medical.service;

import cn.edu.hdu.innovate.medical.domain.Employee;
import cn.edu.hdu.innovate.medical.query.EmployeeQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;

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

	/**
	 * 高级查询
	 * @param qo
	 * @return
	 */
    PageResult query(EmployeeQueryObject qo);

	/**
	 * 分配一个employee
	 * @param username
	 * @param password
	 */
	void allotEmployee(String username, String password);

	/**
	 * 更新员工的一条记录
	 *
	 * @param employee
	 */
    void update(Employee employee);
}
