package cn.edu.hdu.innovate.medical.vo;


import cn.edu.hdu.innovate.medical.domain.BaseDomain;
import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.domain.ComDept;
import cn.edu.hdu.innovate.medical.domain.Employee;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 账单审核模型
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class BillAuditVO {
	
	private Employee employee;	//对应的员工对象
	private Bill bill;			// 对应的账单对象
	
	/**
	 * 返回json处理串
	 * @return
	 */
	public String getJsonString() {
		HashMap<String, Object> json = new HashMap<>();
		json.put("id", bill.getId());
		json.put("jobId", employee.getJobId());
		json.put("realname", employee.getRealname());
		json.put("illnessType", bill.getIllnessTypeDisplay());
		if(bill.getSeriousType() != null) {
			// 重大疾病才会产生seriousType
			json.put("seriousType", bill.getSeriousType().getTitle());
		}
		json.put("hospital", bill.getHospital());
		json.put("applyMoney", bill.getApplyMoney());
		json.put("description", bill.getDescription());
		return JSONObject.toJSONString(json);
	}
	
	/**
	 * 返回当前账单的报销率
	 * @return
	 */
	public String getBillRate() {
		// 注意使用BigDecimal的判断相等应该使用compareTo看返回值是否等于0
		if(bill.getApplyMoney() == null || bill.getAuditMoney() == null || bill.getApplyMoney().compareTo(BigDecimal.ZERO) == 0 || bill.getAuditMoney().compareTo(BigDecimal.ZERO) == 0){
			return "0.00%";
		}
		// 化成百分比提交
		return (bill.getAuditMoney().divide(bill.getApplyMoney(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")) + "%";
	}
}
