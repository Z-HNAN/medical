package cn.edu.hdu.zhn.medical.base.service;



import java.math.BigDecimal;


/**
 * 银行对接服务
 * @author Z_HNAN
 *
 */
public interface IBankService {
	
	/**
	  * 银行对接方法
	 * @param bankNumber  银行卡号
	 * @param amount 		转账金额
	 * @return
	 */
	boolean transfer(String bankNumber, BigDecimal amount);
}
