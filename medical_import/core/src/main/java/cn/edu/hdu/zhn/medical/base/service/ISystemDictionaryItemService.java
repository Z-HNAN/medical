package cn.edu.hdu.zhn.medical.base.service;

import java.util.List;

import cn.edu.hdu.zhn.medical.base.domain.SystemDictionaryItem;
import cn.edu.hdu.zhn.medical.base.query.PageResult;
import cn.edu.hdu.zhn.medical.base.query.SystemDictionaryQueryObject;

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
