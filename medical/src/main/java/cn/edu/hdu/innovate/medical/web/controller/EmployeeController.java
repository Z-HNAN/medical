package cn.edu.hdu.innovate.medical.web.controller;

import cn.edu.hdu.innovate.medical.domain.ComDept;
import cn.edu.hdu.innovate.medical.domain.Employee;
import cn.edu.hdu.innovate.medical.domain.MedicalRoom;
import cn.edu.hdu.innovate.medical.query.EmployeeQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.service.IComDeptService;
import cn.edu.hdu.innovate.medical.service.IEmployeeService;
import cn.edu.hdu.innovate.medical.service.IMedicalRoomService;
import cn.edu.hdu.innovate.medical.util.JSONResult;
import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 员工操作相关的controller
 *
 * @Auther: Z_HNan
 * @Date: 2018/11/29 13:42
 * @Description:
 */
@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IComDeptService comDeptService;

    @Autowired
    private IMedicalRoomService medicalRoomService;

    @RequestMapping("employeeList")
    @RequiredLogin
    public String employeeList(@ModelAttribute("qo") EmployeeQueryObject qo, Model model) {
        // 获取当前的医务室信息
        MedicalRoom currentRoom = this.medicalRoomService.getByPrincipalId(UserContext.getCurrent().getId());
        // 设置查询当前公司的员工
        qo.setComId(currentRoom.getCom().getId());
        PageResult pageResult = this.employeeService.query(qo);

        model.addAttribute("coms",this.comDeptService.listCom());
        model.addAttribute("pageResult", pageResult);
        return "mgrsite/dept/employee_list";
    }

    /**
     * 新增员工的操作
     *
     * @return
     */
    @RequestMapping("addEmployee")
    @ResponseBody
    public JSONResult addEmployee(String username, String password) {
        JSONResult json = new JSONResult();
        try {
            this.employeeService.allotEmployee(username, password);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    /**
     * @return
     */
    @RequestMapping("updateEmployee")
    @ResponseBody
    public JSONResult updateEmployee(Employee employee, @RequestParam("dept") Long deptId) {
        JSONResult json = new JSONResult();
        try {
            ComDept dept = new ComDept();
            dept.setId(deptId);
            employee.setComDept(dept);
            this.employeeService.update(employee);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }


}
