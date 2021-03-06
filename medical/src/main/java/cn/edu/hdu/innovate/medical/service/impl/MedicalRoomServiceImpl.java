package cn.edu.hdu.innovate.medical.service.impl;


import cn.edu.hdu.innovate.medical.domain.*;
import cn.edu.hdu.innovate.medical.mapper.MedicalRoomMapper;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import cn.edu.hdu.innovate.medical.service.*;
import cn.edu.hdu.innovate.medical.vo.PassMoneyBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 医务室相关服务类
 * @author Z_HNAN
 *
 */
@Service
public class MedicalRoomServiceImpl implements IMedicalRoomService {
	
	@Autowired
	private ILogininfoService logininfoService;
	
	@Autowired
	private IComDeptService comDeptService;
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IBankService bankService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private MedicalRoomMapper medicalRoomMapper;

	@Override
	public void createMedicalRoom(String username, String password, Long comId, BigDecimal budgetAmount) {
		// 封装负责人 principal对象
		Logininfo principal = new Logininfo();
		principal.setUsername(username);
		principal.setPassword(password);
		principal.setUserType(Logininfo.USER_TYPE_MEDICAL_ROOM);
		principal.setState(Logininfo.STATE_NORMAL);
		this.logininfoService.save(principal);
		
		// 获取 com对象
		ComDept com = this.comDeptService.get(comId);
		if(com == null) {
			throw new RuntimeException("该公司分公司不存在");
		}
		
		// 封装 medicalRoom 医务室对象
		MedicalRoom medicalRoom = new MedicalRoom();
		medicalRoom.setPrincipal(principal);
		medicalRoom.setCom(com);
		medicalRoom.setBudgetAmount(budgetAmount);
		medicalRoom.setUseAmount(BigDecimal.ZERO);
		
		// 插入medicalRoom结果
		this.medicalRoomMapper.insert(medicalRoom);
	}

	@Override
	public MedicalRoom get(Long medicalDept) {
		return this.medicalRoomMapper.selectByPrimaryKey(medicalDept);
	}

	@Override
	public List<MedicalRoom> listAll() {
		return this.medicalRoomMapper.selectAll();
	}

	@Override
	public MedicalRoom getByPrincipalId(Long id) {
		return this.medicalRoomMapper.selectByPrincipalId(id);
	}

	@Override
	public void updateAmount(MedicalRoom medicalRoom) {
		this.medicalRoomMapper.updateAmount(medicalRoom);
	}

	@Override
	public void passMoney(Long medicalDept, BigDecimal currentAmount) {
		MedicalRoom medicalRoom = this.get(medicalDept);
		
		// 判断前后台 请求的金额是否相同
		BillAuditQueryObject qo = new BillAuditQueryObject();
		qo.setBillState(Bill.BILL_STATE_WAIT_MONEY);
		qo.setComId(medicalRoom.getCom().getId());
		BigDecimal mgrCurrentAmount = this.billService.getBillsMoneyByComIdAndBillState(qo);
		if(mgrCurrentAmount.compareTo(currentAmount) != 0) {
			// 前后台数据 所得到的审核金额不一致
			throw new RuntimeException("前后台金额不一致，请重新检查审核金额，或联系管理员");
		}
		
		// 准备好订单
		List<Bill> bills =  this.billService.listPassMoneyBillByDeptIdAndBillState(medicalRoom.getCom().getId(), Bill.BILL_STATE_WAIT_MONEY);
		// 准备放款数据
		ArrayList<PassMoneyBillVO> transList = new ArrayList<>();
		for (Bill bill : bills) {
			Employee employee = this.employeeService.getById(bill.getApplier().getId());
			PassMoneyBillVO passMoneyBillVO = new PassMoneyBillVO();
			passMoneyBillVO.setBankNumber(employee.getBankNumber());
			passMoneyBillVO.setAmount(bill.getAuditMoney());
			// ========= 可以考虑再次基础上 怎加一个放款VO的log 这里省略
			
			// 请求银行放款 --审核相同 开始放款 请求银行API  
			boolean ret = this.bankService.transfer(passMoneyBillVO.getBankNumber(), passMoneyBillVO.getAmount());
			if(ret == true) {
				// 转账成功
				// 修改账户信息
				Bill historyBill = this.billService.get(bill.getId());
				historyBill.setBillState(Bill.BILL_STATE_PASS);
				this.billService.update(historyBill);
			}else {
				// 转账失败 放入log中
				System.out.println("=============================");
				System.out.println("当前--转账失败");
				System.out.println("工号:" + employee.getJobId());
				System.out.println("失败金额" + bill.getAuditMoney());
				System.out.println("=============================");
			}
			
		}
		
	}

	
	
}
