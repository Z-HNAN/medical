package cn.edu.hdu.zhn.medical.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hdu.zhn.medical.base.domain.Logininfo;
import cn.edu.hdu.zhn.medical.base.query.BillHistoryQueryObject;
import cn.edu.hdu.zhn.medical.base.service.IBillService;
import cn.edu.hdu.zhn.medical.base.util.UserContext;
import cn.edu.hdu.zhn.medical.website.anno.RequiredLogin;

@Controller
public class BillHistoryController {
	
	
	@Autowired
	private IBillService billService;

	@RequestMapping("/billHistory")
	@RequiredLogin
	public String billHistory(@ModelAttribute("qo")BillHistoryQueryObject qo,Model model){
		Logininfo current = UserContext.getCurrent();
		qo.setLogininfoId(current.getId());
		model.addAttribute("pageResult", this.billService.query(qo));
		return "billHistory";
	}
}
