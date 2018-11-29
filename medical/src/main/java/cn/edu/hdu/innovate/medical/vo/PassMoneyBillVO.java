package cn.edu.hdu.innovate.medical.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
