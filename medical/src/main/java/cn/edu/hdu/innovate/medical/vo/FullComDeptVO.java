package cn.edu.hdu.innovate.medical.vo;

import cn.edu.hdu.innovate.medical.domain.ComDept;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 完整的 公司已经下属分部门的 vo对象
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class FullComDeptVO {
	
	private ComDept com;
	private List<ComDept> depts = new ArrayList<ComDept>();
	
	/**
	 * 返回提示的json串(新增分部门使用)
	 * @return
	 */
	public String getTips() {
		HashMap<String, Object> json = new HashMap<>();
		json.put("title", com.getComDeptName() + " - 新增部门");
		json.put("label", "新部门名称");
		json.put("type", ComDept.TYPE_DEPT);
		json.put("parentId", com.getId());
		return JSONObject.toJSONString(json);
	}
	
	
}
