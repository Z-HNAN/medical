package cn.edu.hdu.innovate.medical.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 医务室卫生所模型
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class MedicalRoom extends BaseDomain{
	
	private Logininfo principal;	// 对应的负责人
	private BigDecimal budgetAmount;	// 总的预算金额
	private BigDecimal useAmount;		// 已使用金额
	private ComDept com;		// 对应的公司的
	
	/**
	 * 计算 剩余可用金额
	 * @return
	 */
	public BigDecimal getRemainAmount() {
		return budgetAmount.subtract(useAmount);
	}
	
	
}
