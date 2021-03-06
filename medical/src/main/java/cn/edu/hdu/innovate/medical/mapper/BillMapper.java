package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import cn.edu.hdu.innovate.medical.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BillMapper {
	int insert(Bill record);

	Bill selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Bill record);

	/**
	 * 查询此用户对应的所有账单
	 * @param id
	 * @return
	 */
	List<Bill> listByEmpId(Long id);

	/**
	 * 高级查询条数（要带用户id）
	 * @param qo
	 * @return
	 */
	int queryForCount(QueryObject qo);

	/**
	 * 查询符合条件的用户数（要带用户id）
	 * @param qo
	 * @return
	 */
	List<Bill> queryList(QueryObject qo);

	/**
	 * 高级查询条数（根据公司id查询此分公司对应的条数）
	 * @param qo
	 * @return
	 */
	int queryInComIdForCount(BillAuditQueryObject qo);

	/**
	 * 高级查询结果（根据公司id查询此分公司下对应的所有的bill）
	 * @param qo
	 * @return
	 */
	List<Bill> queryInComIdAuditList(BillAuditQueryObject qo);

	/**
	 * 查询某个公司下 此状态的所有账单总和
	 * @param qo
	 * @return
	 */
	BigDecimal getBillsMoneyByComIdAndBillState(BillAuditQueryObject qo);

	/**
	 * 获得放款信息账单（apply  auditMoney）通过公司Id 与  账单状态
	 * @return
	 */
	List<Bill> listPassMoneyBillByDeptIdAndBillState(@Param("depts") List<Integer> depts, @Param("billState") int billState);
	
	
}