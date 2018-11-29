package cn.edu.hdu.innovate.medical.service;


import cn.edu.hdu.innovate.medical.domain.SystemDictionaryItem;
import cn.edu.hdu.innovate.medical.query.PageResult;
import cn.edu.hdu.innovate.medical.query.SystemDictionaryQueryObject;

import java.util.List;

/**
 * 数据字典明细的服务类
 * @author Z_HNAN
 *
 */
public interface ISystemDictionaryItemService {
	
	/**
	 *  高级查询
	 * @return
	 */
	PageResult query(SystemDictionaryQueryObject qo);
	
	/**
	 * 增加一个字典对象
	 */
	void save(SystemDictionaryItem systemDictionaryItem);
	
	/**
	 * 根据字典id逻辑删除一个数据
	 * @param id
	 */
	void remove(Long id);

	/**
	 * 获取存在的大病类型（没有逻辑删除的）
	 * @return
	 */
	List<SystemDictionaryItem> listByNotDel();
}
