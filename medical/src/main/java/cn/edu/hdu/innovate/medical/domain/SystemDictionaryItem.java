package cn.edu.hdu.innovate.medical.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典类型（可维护的疾病类型）
 * @author Z_HNAN
 *
 */
@Getter
@Setter
public class SystemDictionaryItem extends BaseDomain{
	
	public static final int ISDEL_FALSE = 0;	// 没删除
	public static final int ISDEL_TRUE = 1;	// 已删除

	
	private String title;	// 字典标题
	private String description;	// 字典描述
	private int sequence;	// 字典顺序
	private int isdel = ISDEL_FALSE;	// 逻辑删除

}
