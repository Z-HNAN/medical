package cn.edu.hdu.zhn.medical.mgrsite.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.hdu.zhn.medical.base.domain.ComDept;
import cn.edu.hdu.zhn.medical.base.service.IComDeptService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;
import cn.edu.hdu.zhn.medical.base.vo.FullComDeptVO;

@Controller
public class ComDeptController {
	
	@Autowired
	private IComDeptService comDeptService;

	@RequestMapping("comDept")
	public String comDept(Model model) {
		List<FullComDeptVO> comDepts = this.comDeptService.getFullComDept();
		model.addAttribute("comDepts", comDepts);
		return "admin/com_dept";
	}

	@RequestMapping("addComDept")
	@ResponseBody
	public JSONResult addComDept(Long parentComDeptId, int type, String comDeptName) {
		JSONResult json = new JSONResult();
		try {
			if(StringUtils.isBlank(comDeptName)) {
				throw new RuntimeException("新建名称不能为空");
			}
			if(parentComDeptId == null && type == ComDept.TYPE_COM) {
				// ===此时是新建公司===
				ComDept com = new ComDept();
				com.setComDeptName(comDeptName);
				com.setType(ComDept.TYPE_COM);
				this.comDeptService.save(com);
			}else if(parentComDeptId != null && type == ComDept.TYPE_DEPT) {
				// ===此时是新建部门===
				ComDept dept = new ComDept();
				dept.setComDeptName(comDeptName);
				dept.setType(ComDept.TYPE_DEPT);
				// 因为存储时 仅仅用到了com.id 所以不用查询出dept对应的父com 只需要设置进去id即可
				ComDept com = new ComDept();
				com.setId(parentComDeptId);
				dept.setParentComDept(com);
				this.comDeptService.save(dept);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
