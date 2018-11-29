package cn.edu.hdu.innovate.medical.web.controller;


import cn.edu.hdu.innovate.medical.domain.SystemDictionaryItem;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.query.SystemDictionaryQueryObject;
import cn.edu.hdu.innovate.medical.service.ISystemDictionaryItemService;
import cn.edu.hdu.innovate.medical.util.JSONResult;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 大病类型的controller
 * @author Z_HNAN
 *
 */
@Controller
public class SeriousController {

	@Autowired
	private ISystemDictionaryItemService systemDictionaryItemService;

	@RequestMapping("seriousList")
	@RequiredLogin
	public String seriousList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		qo.setIsdel(SystemDictionaryItem.ISDEL_FALSE);
		PageResult pageResult = this.systemDictionaryItemService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "mgrsite/admin/serious_list";
	}

	/**
	 * 新增一个重大疾病
	 * @return
	 */
	@RequestMapping("addSerious")
	@ResponseBody
	public JSONResult addSerious(String title, String description, int sequence) {
		JSONResult json = new JSONResult();
		try {
			// 检查数据合法性
			if (StringUtils.isBlank(title)) {
				throw new RuntimeException("标题不能为空");
			}
			if (StringUtils.isBlank(description)) {
				throw new RuntimeException("描述不能为空");
			}
			SystemDictionaryItem systemDictionaryItem = new SystemDictionaryItem();
			systemDictionaryItem.setTitle(title);
			systemDictionaryItem.setDescription(description);
			systemDictionaryItem.setSequence(sequence);
			this.systemDictionaryItemService.save(systemDictionaryItem);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 逻辑删除疾病的方式
	 */
	@RequestMapping("/removeSerious")
	@ResponseBody
	public JSONResult removeSerious(Long id) {
		JSONResult json = new JSONResult();
		try {
			this.systemDictionaryItemService.remove(id);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
