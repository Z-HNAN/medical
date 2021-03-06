package cn.edu.hdu.innovate.medical.service.impl;


import cn.edu.hdu.innovate.medical.domain.ComDept;
import cn.edu.hdu.innovate.medical.mapper.ComDeptMapper;
import cn.edu.hdu.innovate.medical.service.IComDeptService;
import cn.edu.hdu.innovate.medical.vo.FullComDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComDeptServiceImpl implements IComDeptService {

	@Autowired
	private ComDeptMapper comDeptMapper;
	
	@Override
	public ComDept get(Long comDeptId) {
		return this.comDeptMapper.selectByPrimaryKey(comDeptId);
	}

	@Override
	public int getCountByComDeptType(int comDeptType) {
		return this.comDeptMapper.getCountByComDeptType(comDeptType);
	}

	@Override
	public List<FullComDeptVO> getFullComDept() {
		// 获取所有的公司信息
		int comAmount = this.comDeptMapper.getCountByComDeptType(ComDept.TYPE_COM);
		if(comAmount == 0) {
			// 没有公司  返回一个空集合就可以
			return new ArrayList<FullComDeptVO>();
		}
		
		// 存储结果的list
		ArrayList<FullComDeptVO> comDepts = new ArrayList<>(comAmount);
		List<ComDept> listCom =  this.comDeptMapper.listCom();
		// 循环得出每一个公司下属的部门信息
		for (ComDept comDept : listCom) {
			// 根据公司查询 其下属的部门
			List<ComDept> depts = this.comDeptMapper.listDeptByParentComId(comDept.getId());
			// 封装到fullComDept里面
			FullComDeptVO fullComDeptVO = new FullComDeptVO();
			fullComDeptVO.setCom(comDept);
			fullComDeptVO.setDepts(depts);
			// 装箱到 comDeps里面
			comDepts.add(fullComDeptVO);
		}
		
		// 完成操作 返回此FullComDept 即可
		return comDepts;
	}

	@Override
	public void save(ComDept comDept) {
		this.comDeptMapper.insert(comDept);
	}

	@Override
	public List<ComDept> listCom() {
		return this.comDeptMapper.listCom();
	}

	@Override
	public List<ComDept> listDeptByParentComId(Long comId) {
		return this.comDeptMapper.listDeptByParentComId(comId);
	}

	@Override
	public List<Integer> listDeptIdByComId(Long comId) {
		return this.comDeptMapper.listDeptIdByComId(comId);
	}

	
	
}
