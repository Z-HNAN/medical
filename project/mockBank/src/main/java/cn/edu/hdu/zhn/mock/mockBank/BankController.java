package cn.edu.hdu.zhn.mock.mockBank;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bankService")
public class BankController {
	
	/**
	 * 用户私钥
	 */
	@Value("${mock.bank.privateKey}")
	private String privateKey;
	

	/**
	 * 模拟转账接口
	 * @param bankNumber  银行卡号
	 * @param amount 		转账金额
	 * @param privateKey	转账私钥
	 * @return
	 */
	// http://localhost:8077/bankService/transferAccounts?privateKey=666&bankNumber=66666&amount=100.00
	@RequestMapping("transferAccounts")
	@ResponseBody
	public String transferAccounts(String bankNumber, BigDecimal amount, String privateKey) {
		// 模拟逻辑判断
		if(!this.privateKey.equals(privateKey)) {
			// 如果私钥不匹配
			return "FALSE";
		}
		System.out.println("==============发起转账===============");
		System.out.println("******** " + bankNumber + "*********");
		System.out.println("******成功转入" + amount + "元********");
		System.out.println("==============转账结束===============");
		System.out.println();
		return "SUCCESS";
	}
}