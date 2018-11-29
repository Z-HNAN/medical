package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.SystemDictionaryItem;
import cn.edu.hdu.innovate.medical.query.QueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
	// 逻辑删除的方法
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

	int queryForCount(QueryObject qo);
	List<SystemDictionaryItem> query(QueryObject qo);

}