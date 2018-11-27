package cn.edu.hdu.zhn.medical.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.hdu.zhn.medical.base.domain.ComDept;
import cn.edu.hdu.zhn.medical.base.service.IComDeptService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;

/**
 * 公司部门相关
 * @author Z_HNAN
 *
 */
@Controller
public class ComDeptController {
	
	@Autowired
	private IComDeptService comDeptService;

	/**
	 * 根据comId返回depts
	 * @return
	 */
	@RequestMapping("/listDeptByParentComId")
	@ResponseBody
	public String listDeptByParentComId(Long comId) {
		List<ComDept> depts = this.comDeptService.listDeptByParentComId(comId);
		return JSONObject.toJSONString(depts);
	}
	
}
