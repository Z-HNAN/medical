package cn.edu.hdu.zhn.medical.base.query;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
/**
 * 审核账单查询qo
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class BillAuditQueryObject extends QueryObject{
	
	private Long comId;			// 查询指定分公司的id
	
	private List<Integer> depts;	// comId对应下的所有depts
	
	private int billState;		// 所查询的账单类型
	
	private Long employeeId;	// 具体某个员工信息的 Id
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;		// 开始时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;		// 结束时间
}
