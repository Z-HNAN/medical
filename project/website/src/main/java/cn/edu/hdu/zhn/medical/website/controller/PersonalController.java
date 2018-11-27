package cn.edu.hdu.zhn.medical.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.hdu.zhn.medical.base.domain.ComDept;
import cn.edu.hdu.zhn.medical.base.domain.Employee;
import cn.edu.hdu.zhn.medical.base.service.IComDeptService;
import cn.edu.hdu.zhn.medical.base.service.IEmployeeService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;
import cn.edu.hdu.zhn.medical.base.util.UserContext;
import cn.edu.hdu.zhn.medical.website.anno.RequiredLogin;

/**
 * 个人中心相关的控制器
 * @author Z_HNAN
 *
 */
@Controller
public class PersonalController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IComDeptService comDeptService;

	@RequestMapping("/personal")
	@RequiredLogin
	public String personal(Model model) {
		Employee employee = employeeService.getById(UserContext.getCurrent().getId()); 
		// 判断当前 logininfo 是否关联了一个 employee 选择是否进行 编辑或是展示
		if(employee == null) {
			// 还未进行过登记
			List<ComDept> coms = comDeptService.listCom();
			model.addAttribute("coms", coms);  
			// 将总的公司信息 放入员工页面中
			return "personal_edit";
		}else {
			// 已经绑定过了employee
			model.addAttribute("employee", employee);
			ComDept dept = employee.getComDept();
			ComDept com = dept.getParentComDept();
			model.addAttribute("deptname", dept.getComDeptName());
			model.addAttribute("comname", com.getComDeptName());
			return "personal";
		}
	}

	@RequestMapping("/saveEmployee")
	@ResponseBody
	public JSONResult saveEmployee(Employee employee, Long dept) {
		JSONResult json = new JSONResult();
		try {
			this.employeeService.save(employee, dept);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
