package cn.edu.hdu.zhn.medical.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.zhn.medical.base.domain.ComDept;
import cn.edu.hdu.zhn.medical.base.domain.Employee;
import cn.edu.hdu.zhn.medical.base.mapper.EmployeeMapper;
import cn.edu.hdu.zhn.medical.base.service.IComDeptService;
import cn.edu.hdu.zhn.medical.base.service.IEmployeeService;
import cn.edu.hdu.zhn.medical.base.util.UserContext;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private IComDeptService comDeptService;

	@Override
	public Employee getById(Long id) {
		return this.employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Employee employee, Long dept) {
		// 为当前员工 添加部门属性
		ComDept comDept = this.comDeptService.get(dept);
		if (comDept == null) {
			throw new RuntimeException("选择的部门信息有误");
		}
		employee.setId(UserContext.getCurrent().getId());
		employee.setComDept(comDept);
		//======headImg=====
		// employee.setHeadImg();
		//======headImg=====
		this.employeeMapper.insert(employee);
	}

	@Override
	public Long getIdByJobIdOrRealname(String jobId, String realname) {
		return this.employeeMapper.getIdByJobIdOrRealname(jobId, realname);
	}

	
}