package cn.edu.hdu.zhn.medical.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillHistoryQueryObject extends QueryObject{
	
	// 必须元素
	private Long logininfoId;	// 查询的人id
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;		// 开始时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;		// 结束时间
}