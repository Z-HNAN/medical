package cn.edu.hdu.innovate.medical.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询出的页面信息
 * 
 * @author Maibenben
 *
 */
@Getter
@Setter
public class PageResult {

	private List<?> list; // 存放的内容
	private int count; // 数据的总条数

	private int currentPage; //当前页面
	private int pageSize; //当前页面

	private int totalPage; //总页数

	private int beginPage = 1; //第一页
	private int prevPage; //上一页
	private int nextPage; //下一页

	/**
	 * 为一个构造方法
	 * @param list		要存放的数据
	 * @param count		数据的总条数
	 * @param currentPage	当前页数
	 * @param pageSize		当前的每页条数
	 */
	public PageResult(List<?> list, int count, int currentPage, int pageSize) {
		this.list = list;
		this.count = count;
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		// 初始化totalPage
		this.totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	}
	
	/**
	 * 返回一个空的PageResult
	 * @param pageSize
	 * @return
	 */
	public static PageResult empty(int pageSize){
		return new PageResult(new ArrayList<>(), 0, 1, pageSize);
	}

	/**
	 * 返回上一页的逻辑
	 * @return
	 */
	public int getPrevPage() {
		return this.currentPage - 1 < 1 ? this.beginPage : this.currentPage - 1;
	}

	/**
	 * 返回下一页的逻辑
	 * @return
	 */
	public int getNextPage() {
		return this.currentPage + 1 > this.totalPage ? this.totalPage : this.currentPage + 1;
	}

}
