package cn.edu.hdu.innovate.medical.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 高级查询对象
 * 
 * @author Maibenben
 *
 */
@Getter
@Setter
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 5;

	/**
	 * 返回物理分页的开始条数  limit {start}, {pageSizes}
	 * @return
	 */
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}
}
