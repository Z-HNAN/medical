package cn.edu.hdu.zhn.medical.base.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import cn.edu.hdu.zhn.medical.base.service.IBankService;
import cn.edu.hdu.zhn.medical.base.util.HttpUtil;

@Service
@PropertySource({"classpath:bussiness.properties"})
public class BankServiceImpl implements IBankService{
	
	@Value("${bank.trans.api}")
	private String bankTransAPI;
	
	@Value("${bank.privateKey}")
	private String privateKey;

	@Override
	public boolean transfer(String bankNumber, BigDecimal amount) {
		HashMap<String, String> params = new HashMap<>();
		params.put("bankNumber", bankNumber);
		params.put("amount", amount.toString());
		params.put("privateKey", this.privateKey);
		try {
			String ret = HttpUtil.sendHttpRequest(this.bankTransAPI, params);
			if(ret.equalsIgnoreCase("success")) {
				// 返回成功 转账成功
				return true;
			}
			// 转账失败
			return false;
		} catch (Exception e) {
			throw new RuntimeException("银行服务器出现问题");
		}
		
	}

	
}