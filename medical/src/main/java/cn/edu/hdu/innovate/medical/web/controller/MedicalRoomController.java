package cn.edu.hdu.innovate.medical.web.controller;


import cn.edu.hdu.innovate.medical.domain.MedicalRoom;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import cn.edu.hdu.innovate.medical.service.IComDeptService;
import cn.edu.hdu.innovate.medical.service.ILogininfoService;
import cn.edu.hdu.innovate.medical.service.IMedicalRoomService;
import cn.edu.hdu.innovate.medical.util.JSONResult;
import cn.edu.hdu.innovate.medical.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class MedicalRoomController {

	@Autowired
	private IMedicalRoomService medicalRoomService;

	@Autowired
	private IComDeptService comDeptService;

	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 医务室管理界面
	 * @return
	 */
	@RequestMapping("medicalList")
	@RequiredLogin
	public String medicalList(Model model, Long medicalDept) {
		if (medicalDept != null) {
			// 是查询跳过来的界面，将medical查询出放进去
			MedicalRoom medicalRoom = this.medicalRoomService.get(medicalDept);
			model.addAttribute("medicalRoom", medicalRoom);
		}
		model.addAttribute("coms", this.comDeptService.listCom());
		model.addAttribute("medicalRooms", this.medicalRoomService.listAll());
		return "mgrsite/com/medical_list";
	}

	/**
	 * 新增医务室
	 * @param username 负责人username
	 * @param password 负责人password
	 * @param comId  对应的公司
	 * @param budgetAmount  初始总报销金额
	 * @return
	 */
	@RequestMapping("addMedical")
	@ResponseBody
	public JSONResult addMedical(String username, String password, Long comId, BigDecimal budgetAmount) {
		JSONResult json = new JSONResult();
		try {
			this.medicalRoomService.createMedicalRoom(username, password, comId, budgetAmount);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 医务室详情页
	 * @param model
	 * @return
	 */
	@RequestMapping("medicalRoomInfo")
	@RequiredLogin
	public String medicalRoomInfo(Model model) {
		// 放入当前医务室信息
		model.addAttribute("medicalRoom", this.medicalRoomService.getByPrincipalId(UserContext.getCurrent().getId()));
		return "mgrsite/dept/medical_room_info";
	}
	
	/**
	 * 卫生所放款的方法
	 * @return
	 */
	@RequestMapping("passMoney")
	@ResponseBody
	public JSONResult passMoney(Long medicalDept, BigDecimal currentAmount) {
		JSONResult json = new JSONResult();
		try {
			// 交给medicalService 进行放款操作
			this.medicalRoomService.passMoney(medicalDept, currentAmount);
			// 转账结束后修改 医务室的信息
			MedicalRoom medicalRoom = this.medicalRoomService.get(medicalDept);
			medicalRoom.setUseAmount(medicalRoom.getUseAmount().add(currentAmount));
			this.medicalRoomService.updateAmount(medicalRoom);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}
	

}
