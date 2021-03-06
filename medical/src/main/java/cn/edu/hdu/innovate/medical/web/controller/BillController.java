package cn.edu.hdu.innovate.medical.web.controller;


import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.domain.MedicalRoom;
import cn.edu.hdu.innovate.medical.domain.SystemDictionaryItem;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.service.IBillService;
import cn.edu.hdu.innovate.medical.service.IEmployeeService;
import cn.edu.hdu.innovate.medical.service.IMedicalRoomService;
import cn.edu.hdu.innovate.medical.service.ISystemDictionaryItemService;
import cn.edu.hdu.innovate.medical.util.JSONResult;
import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.web.anno.RequiredLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 报销单相关
 * @author Z_HNAN
 *
 */
@Controller
public class BillController {

	@Autowired
	private IBillService billService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IMedicalRoomService medicalRoomService;


	@Autowired
	private ISystemDictionaryItemService systemDictionaryItemService;
	

	@RequestMapping("/bill")
	@RequiredLogin
	public String bill(Model model) {
		List<SystemDictionaryItem> seriousList = this.systemDictionaryItemService.listByNotDel();
		model.addAttribute("seriousList", seriousList);
		return "website/bill";
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

	/**
	 * 医务室审核账单的方法
	 * @param model
	 * @return
	 */
	@RequestMapping("auditBill")
	@ResponseBody
	public JSONResult auditBill(Model model, int state, Long id, BigDecimal auditMoney, String note) {
		JSONResult json = new JSONResult();
		try {
			Bill bill = this.billService.get(id);
			// 审核相关操作
			bill.setAuditor(UserContext.getCurrent());
			bill.setAuditDate(new Date());
			bill.setNote(note);
			bill.setAuditMoney(auditMoney);
			// 更改当前订单状态
			if (state == Bill.BILL_STATE_WAIT_PASS) { // 审核成功,等待放款
				bill.setBillState(Bill.BILL_STATE_WAIT_PASS);
			} else if (state == Bill.BILL_STATE_REJECT) { // 审核失败
				bill.setBillState(Bill.BILL_STATE_REJECT);
			} else {
				throw new RuntimeException("操作审核异常");
			}
			// 跟新bill操作
			this.billService.update(bill);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 医务室查看账单详列表的方法
	 * @param model
	 * @return
	 */
	@RequestMapping("billAuditListDept")
	@RequiredLogin
	public String billAuditListDept(@ModelAttribute("qo") BillAuditQueryObject qo, Model model, String jobId,
									String realname) {

		// 判断是否需要查询具体的员工信息
		if (StringUtils.isNotBlank(jobId) || StringUtils.isNotBlank(realname)) {
			Long employeeId = this.employeeService.getIdByJobIdOrRealname(jobId, realname);
			qo.setEmployeeId(employeeId);
		}

		MedicalRoom medicalRoom = this.medicalRoomService.getByPrincipalId(UserContext.getCurrent().getId());
		// 设置 当前comId为审核人所在的公司
		qo.setComId(medicalRoom.getCom().getId());
		// 设置当前订单的 状态为审核中的状态
		qo.setBillState(Bill.BILL_STATE_PENDING);

		model.addAttribute("pageResult", this.billService.queryAudit(qo));
		return "mgrsite/dept/bill_list";
	}

	@RequestMapping("billAuditListCom")
	@RequiredLogin
	public String billAuditListCom(Long medicalDept,Integer currentPage, Model model) {
		MedicalRoom medicalRoom = this.medicalRoomService.get(medicalDept);

		if (medicalDept != null && medicalDept > -1) {
			// 有了具体的查询公司,查询某一个具体的列表项
			// 高级查询+分页得出中间小块的部分
			BillAuditQueryObject qo = new BillAuditQueryObject();
			qo.setBillState(Bill.BILL_STATE_WAIT_MONEY);
			qo.setComId(medicalRoom.getCom().getId());
			// 保证currentPage从第一页开始
			qo.setCurrentPage(currentPage == null?1:currentPage);
			// =====放入账单总和
			BigDecimal currentAmount = this.billService.getBillsMoneyByComIdAndBillState(qo);
			model.addAttribute("medicalDept", medicalDept);
			model.addAttribute("currentAmount", currentAmount);
			// =====放入pageResult中
			model.addAttribute("pageResult", this.billService.queryAudit(qo));
		}
		// 放入所有的分公司
		List<MedicalRoom> medicalRooms = this.medicalRoomService.listAll();
		model.addAttribute("medicalRooms", medicalRooms);
		model.addAttribute("medicalRoom", medicalRoom);
		return "mgrsite/com/audit_bills_list";
	}

	@RequestMapping("/sendBillList")
	@RequiredLogin
	public String sendBillList(@ModelAttribute("qo") BillAuditQueryObject qo, Model model) {
		// 放入对应公司的账单 并且 要求bill的状态为 等待提交的状态（已经审核过的状态）
		qo.setBillState(Bill.BILL_STATE_WAIT_PASS);
		// 查询此公司下的bill信息
		MedicalRoom medicalRoom = this.medicalRoomService.getByPrincipalId(UserContext.getCurrent().getId());
		qo.setComId(medicalRoom.getCom().getId());
		PageResult pageResult = this.billService.queryAudit(qo);
		// 放入model中
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("medicalRoom", medicalRoom);
		return "mgrsite/dept/send_bill_list";
	}

	/**
	 * 提交到卫生所准备放款
	 * @return
	 */
	@RequestMapping("passBills")
	@ResponseBody
	public JSONResult passBills(@RequestParam(value = "billIds[]") List<Long> billIds) {
		JSONResult json = new JSONResult();
		try {
			// 修改每一份账单状态
			if (billIds == null) {
				// 未提交任何审核
				throw new RuntimeException("还未提交任何审核信息");
			}
			MedicalRoom medicalRoom = this.medicalRoomService.getByPrincipalId(UserContext.getCurrent().getId());
			BigDecimal sum = BigDecimal.ZERO;
			for (Long id : billIds) {
				// 修改每一份账单的状态
				Bill bill = this.billService.get(id);
				bill.setBillState(Bill.BILL_STATE_WAIT_MONEY);
				sum = sum.add(bill.getAuditMoney());
				this.billService.update(bill);
			}
			// 判断报销金是否合理 并且 做报销金管理
			if (medicalRoom.getRemainAmount().compareTo(sum) < 0) {
				// 报销不合理 则 进行回退
				for (Long id : billIds) {
					Bill bill = this.billService.get(id);
					bill.setBillState(Bill.BILL_STATE_WAIT_PASS);
					sum = sum.add(bill.getAuditMoney());
					this.billService.update(bill);
				}
				throw new RuntimeException("申请报销金 超出 部门的预算");
			}

		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
