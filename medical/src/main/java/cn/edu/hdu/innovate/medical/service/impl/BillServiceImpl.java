package cn.edu.hdu.innovate.medical.service.impl;


import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.domain.Employee;
import cn.edu.hdu.innovate.medical.mapper.BillMapper;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import cn.edu.hdu.innovate.medical.query.BillHistoryQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.service.IBillService;
import cn.edu.hdu.innovate.medical.service.IComDeptService;
import cn.edu.hdu.innovate.medical.service.IEmployeeService;
import cn.edu.hdu.innovate.medical.util.UserContext;
import cn.edu.hdu.innovate.medical.vo.BillAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IComDeptService comDeptService;
	
	@Autowired
	private BillMapper billMapper;

	@Override
	public void createBill(Bill br) {
		// 得到这次 请求的员工对象
		Employee employee = this.employeeService.getById(UserContext.getCurrent().getId());
		// 创建一个新的 账单对象
		Bill bill = new Bill();
		// 将前台传进的值重新封装一次
		bill.setApplier(UserContext.getCurrent());
		bill.setApplyDate(new Date());
		bill.setApplyMoney(br.getApplyMoney());
		bill.setBillState(Bill.BILL_STATE_PENDING);
		//================
		// bill.setBillImg(billImg);
		//================
		bill.setComDeptId(employee.getComDept().getId());
		bill.setDescription(br.getDescription());
		bill.setHospital(br.getHospital());
		bill.setIllnessType(br.getIllnessType());
		bill.setInHospitalDate(br.getInHospitalDate());
		bill.setOutHospitalDate(br.getOutHospitalDate());
		if(br.getIllnessType() == Bill.ILLNESS_TYPE_SERIOUS) {
			// 是大病类型  若不是大病类型，则无需关联 大病的具体种类
			bill.setSeriousType(br.getSeriousType());
		}
		// 此次请求的 报销bill 封装完成
		this.billMapper.insert(bill);
	}

	@Override
	public List<Bill> listByEmpId(Long id) {
		return this.billMapper.listByEmpId(id);
	}

	@Override
	public PageResult query(BillHistoryQueryObject qo) {
		int ret = this.billMapper.queryForCount(qo);
		if(ret == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Bill> bills = this.billMapper.queryList(qo);
		return new PageResult(bills, ret, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public PageResult queryAudit(BillAuditQueryObject qo) {
		if(qo.getComId() == null) {
			throw new RuntimeException("公司id不能为空");
		}
		
		List<Integer> depts = this.comDeptService.listDeptIdByComId(qo.getComId());
		// 查询出部门 并将部门depts都放到qo当中
		qo.setDepts(depts);
		
		int ret = this.billMapper.queryInComIdForCount(qo);
		// 便利部门查询出 每个部门遍历的账单
		if(ret == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		// 将bills中的每一个bill 与employee关联起来
		List<Bill> bills = this.billMapper.queryInComIdAuditList(qo);
		List<BillAuditVO> billVOs = new ArrayList<>(bills.size());
		for (Bill bill : bills) {
			BillAuditVO billAuditVO = new BillAuditVO();
			billAuditVO.setBill(bill);
			billAuditVO.setEmployee(this.employeeService.getById(bill.getApplier().getId()));
			billVOs.add(billAuditVO);
		}
		return new PageResult(billVOs, ret, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Bill get(Long id) {
		return this.billMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Bill bill) {
		this.billMapper.updateByPrimaryKey(bill);
	}

	@Override
	public BigDecimal getBillsMoneyByComIdAndBillState(BillAuditQueryObject qo) {
		return this.billMapper.getBillsMoneyByComIdAndBillState(qo);
	}

	@Override
	public List<Bill> listPassMoneyBillByDeptIdAndBillState(Long comId, int billState) {
		// 查询公司对应下的 的一些deptsId
		List<Integer> depts = this.comDeptService.listDeptIdByComId(comId);
		return this.billMapper.listPassMoneyBillByDeptIdAndBillState(depts, billState);
	}

	
	
}
