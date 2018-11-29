package cn.edu.hdu.innovate.medical.web.controller;

import cn.edu.hdu.innovate.medical.domain.Logininfo;
import cn.edu.hdu.innovate.medical.query.BillHistoryQueryObject;
import cn.edu.hdu.innovate.medical.service.IBillService;
import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BillHistoryController {
	
	
	@Autowired
	private IBillService billService;

	@RequestMapping("/billHistory")
	@RequiredLogin
	public String billHistory(@ModelAttribute("qo")BillHistoryQueryObject qo, Model model){
		Logininfo current = UserContext.getCurrent();
		qo.setLogininfoId(current.getId());
		model.addAttribute("pageResult", this.billService.query(qo));
		return "website/billHistory";
	}
}
