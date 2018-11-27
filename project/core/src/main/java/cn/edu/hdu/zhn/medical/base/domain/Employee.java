package cn.edu.hdu.zhn.medical.base.domain;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工模型
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class Employee extends BaseDomain {

	public static final int GENDER_MALE = 0; // 性别男
	public static final int GENDER_FEMALE = 1; // 性别女

	private String realname; // 真实姓名
	private int gender; // 性别
	private String idNumber; // 身份证号
	private String headImg; // 本人照片
	private String phoneNumber; // 手机号
	private String jobId; // 员工工号
	private String bankNumber; // 银行卡号
	private ComDept comDept; // 所在单位 部门

	/**
	 * 性别的展示方法
	 * @return
	 */
	public String getGenderDisplay() {
		switch (gender) {
		case GENDER_MALE:
			return "男";
		case GENDER_FEMALE:
			return "女";
		default:
			return "";
		}
	}
	
	
	/**
	 * 返回json相关json数据
	 * @return
	 */
	public String getJsonString() {
		HashMap<String, Object> json = new HashMap<>();
		json.put("realname", realname);
		json.put("gender", gender);
		json.put("idNumber", idNumber);
		json.put("headImg", headImg);
		json.put("phoneNumber", phoneNumber);
		json.put("jobId", jobId);
		json.put("bankNumber", bankNumber);
		json.put("dept", comDept.getComDeptName());
		json.put("com", comDept.getParentComDept().getComDeptName());
		return JSONObject.toJSONString(json);
	}
}
