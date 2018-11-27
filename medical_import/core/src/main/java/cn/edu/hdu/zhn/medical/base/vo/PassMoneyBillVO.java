package cn.edu.hdu.zhn.medical.base.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 放款模型
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class PassMoneyBillVO {
	
	// 银行卡号
	private String bankNumber;
	// 汇款金额
	private BigDecimal amount;
}
