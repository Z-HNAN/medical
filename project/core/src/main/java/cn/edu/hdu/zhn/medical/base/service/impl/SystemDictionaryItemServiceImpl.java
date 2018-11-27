package cn.edu.hdu.zhn.medical.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.zhn.medical.base.domain.SystemDictionaryItem;
import cn.edu.hdu.zhn.medical.base.mapper.SystemDictionaryItemMapper;
import cn.edu.hdu.zhn.medical.base.query.PageResult;
import cn.edu.hdu.zhn.medical.base.query.SystemDictionaryQueryObject;
import cn.edu.hdu.zhn.medical.base.service.ISystemDictionaryItemService;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {

	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;

	@Override
	public PageResult query(SystemDictionaryQueryObject qo) {
		int ret = this.systemDictionaryItemMapper.queryForCount(qo);
		if (ret > 0) {
			List<SystemDictionaryItem> list = this.systemDictionaryItemMapper.query(qo);
			return new PageResult(list, ret, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void save(SystemDictionaryItem systemDictionaryItem) {
		this.systemDictionaryItemMapper.insert(systemDictionaryItem);
	}

	@Override
	public void remove(Long id) {
		this.systemDictionaryItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SystemDictionaryItem> listByNotDel() {
		SystemDictionaryQueryObject qo = new SystemDictionaryQueryObject();
		qo.setIsdel(SystemDictionaryItem.ISDEL_FALSE);
		return this.systemDictionaryItemMapper.query(qo);
	}

	
	
}
