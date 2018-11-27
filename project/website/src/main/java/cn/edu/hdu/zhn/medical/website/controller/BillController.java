package cn.edu.hdu.zhn.medical.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.hdu.zhn.medical.base.domain.Bill;
import cn.edu.hdu.zhn.medical.base.domain.SystemDictionaryItem;
import cn.edu.hdu.zhn.medical.base.service.IBillService;
import cn.edu.hdu.zhn.medical.base.service.ISystemDictionaryItemService;
import cn.edu.hdu.zhn.medical.base.util.JSONResult;
import cn.edu.hdu.zhn.medical.website.anno.RequiredLogin;

/**
 * 报销单相关
 * @author Z_HNAN
 *
 */
@Controller
public class BillController {

	@Autowired
	private ISystemDictionaryItemService systemDictionaryItemService;
	
	@Autowired
	private IBillService billService;
	
	
	@RequestMapping("/bill")
	@RequiredLogin
	public String bill(Model model) {
		List<SystemDictionaryItem> seriousList = this.systemDictionaryItemService.listByNotDel();
		model.addAttribute("seriousList", seriousList);
		return "bill";
	}
	
	@RequestMapping("/createBill")
	@ResponseBody
	public JSONResult createBill(Bill br) {
		JSONResult json = new JSONResult();
		try {
			this.billService.createBill(br);
		} catch (Exception e) {
			json.setSuccess(false);  
			json.setMsg(e.getMessage());
		}
		return json;
	}
}