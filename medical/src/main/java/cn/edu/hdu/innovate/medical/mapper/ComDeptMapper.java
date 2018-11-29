package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.ComDept;

import java.util.List;

public interface ComDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ComDept record);

    ComDept selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ComDept record);

    /**
     * 根据 comDeptType 查询符合条件的部门数量
     * @param comDeptType
     * @return
     */
	int getCountByComDeptType(int comDeptType);

	/**
	 * 返回所有的分公司信息
	 * @return
	 */
	List<ComDept> listCom();

	/**
	 * 根据公司的id查询出此公司下的 部门
	 * @param id
	 * @return
	 */
	List<ComDept> listDeptByParentComId(Long id);

	/**
	 * 根据公司id 查询其分部门的下的所有deptId
	 * @param comId
	 * @return
	 */
	List<Integer> listDeptIdByComId(Long comId);
}