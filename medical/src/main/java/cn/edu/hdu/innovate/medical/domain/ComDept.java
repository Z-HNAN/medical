package cn.edu.hdu.innovate.medical.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用来表示公司部门 可理解为一个公司坐标（公司，部门）
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class ComDept extends BaseDomain{
	
	public static final int TYPE_COM = 0;	// 公司类型
	public static final int TYPE_DEPT = 1;	// 部门类型
	
	private String comDeptName;		// 公司或部门名称
	private int type;		// 属于公司还是部门的类型
	private ComDept parentComDept;	// 总则为 空   父部门的对象
	
	
}
