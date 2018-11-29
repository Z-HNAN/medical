package cn.edu.hdu.innovate.medical.service.impl;


import cn.edu.hdu.innovate.medical.domain.ComDept;
import cn.edu.hdu.innovate.medical.domain.Employee;
import cn.edu.hdu.innovate.medical.domain.Logininfo;
import cn.edu.hdu.innovate.medical.mapper.EmployeeMapper;
import cn.edu.hdu.innovate.medical.query.EmployeeQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.service.IComDeptService;
import cn.edu.hdu.innovate.medical.service.IEmployeeService;
import cn.edu.hdu.innovate.medical.service.ILogininfoService;
import cn.edu.hdu.innovate.medical.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private ILogininfoService logininfoService;
	
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

	@Override
	public PageResult query(EmployeeQueryObject qo) {
		if(qo.getComId() == null){
			throw new RuntimeException("公司id不能为空");
		}

        List<Integer> depts =this.comDeptService.listDeptIdByComId(qo.getComId());
		// 将查询出的部门 放入qo中
        qo.setDepts(depts);

        int ret = this.employeeMapper.queryInComIdForCount(qo);
        if(ret == 0){
            return PageResult.empty(qo.getPageSize());
        }
        // 放入pageReustl中
        List<Employee> empts =  this.employeeMapper.queryInComIdList(qo);
        return new PageResult(empts,ret,qo.getCurrentPage(),qo.getPageSize());
    }

	@Override
	public void allotEmployee(String username, String password) {
		Logininfo employee = new Logininfo();
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setState(Logininfo.STATE_NORMAL);
		employee.setUserType(Logininfo.USER_TYPE_EMPLOYEE);
		this.logininfoService.save(employee);
	}

	@Override
	public void update(Employee employee) {
		this.employeeMapper.updateByPrimaryKey(employee);
	}
}
