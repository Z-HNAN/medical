package cn.edu.hdu.innovate.medical.service;


import cn.edu.hdu.innovate.medical.domain.ComDept;
import cn.edu.hdu.innovate.medical.vo.FullComDeptVO;

import java.util.List;

/**
 * 部门信息的服务对象
 * @author Z_HNAN
 *
 */
public interface IComDeptService {

	/**
	 * 根据id查询部门详细信息
	 * @param comDeptId
	 * @return
	 */
	ComDept get(Long comDeptId);
	
	/**
	 * 根据comDeptType查询此种类部门的数量
	 * @param comDeptType
	 * @return
	 */
	int getCountByComDeptType(int comDeptType);

	/**
	 * 得到全部的分公司信息以及下属部门的信息
	 * @return
	 */
	List<FullComDeptVO> getFullComDept();
	
	/**
	 * 存储一条comDept
	 * @param comDept
	 */
	void save(ComDept comDept);
	
	/**
	 * 返回所有的公司信息
	 * @return
	 */
	List<ComDept> listCom();
	
	/**
	 * 根据parentComId 查询此公司下的所有部门
	 * @param comId
	 * @return
	 */
	List<ComDept> listDeptByParentComId(Long comId);

	/**
	 * 根据公司的comId查询其所有的depts的Id
	 * @param comId
	 * @return
	 */
	List<Integer> listDeptIdByComId(Long comId);
}
