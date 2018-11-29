package cn.edu.hdu.innovate.medical.service;


import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import cn.edu.hdu.innovate.medical.query.BillHistoryQueryObject;
import cn.edu.hdu.innovate.medical.query.PageResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * 报销账单服务类
 * @author Z_HNAN
 *
 */
public interface IBillService {

	/**
	 * 创建一个报销单账户
	 * @param br 前台传过来的数据
	 */
	void createBill(Bill br);

	/**
	 * 查询出账户对应的所有Bill
	 * @param id
	 * @return
	 */
	List<Bill> listByEmpId(Long id);
	
	/**
	 * billHistory的高级分页查询
	 * @param qo
	 * @return
	 */
	PageResult query(BillHistoryQueryObject qo);

	/**
	 * BillAudit 的高级分页查询
	 * @param qo
	 * @return
	 */
	PageResult queryAudit(BillAuditQueryObject qo);

	Bill get(Long id);

	/**
	 * 更新操作
	 * @param bill
	 */
	void update(Bill bill);

	/**
	 * 查询某个公司下 此状态的所有账单总和
	 * @param qo
	 * @return
	 */
	BigDecimal getBillsMoneyByComIdAndBillState(BillAuditQueryObject qo);

	/**
	 * 获得放款信息账单（apply  auditMoney）通过公司Id 与  账单状态
	 * @param medicalDept
	 * @param billStateWaitMoney
	 * @return
	 */
	List<Bill> listPassMoneyBillByDeptIdAndBillState(Long comId, int billState);

}
