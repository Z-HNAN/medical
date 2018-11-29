package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.Employee;
import cn.edu.hdu.innovate.medical.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Employee record);

    /**
     * 员工jobId 与realname 得到员工的 ID
     * @param jobId
     * @param realname
     * @return
     */ 
	Long getIdByJobIdOrRealname(@Param("jobId") String jobId, @Param("realname") String realname);

    /**
     * 高级查询 适合条件的 员工数量
     * @param qo
     * @return
     */
    int queryInComIdForCount(EmployeeQueryObject qo);

    /**
     * 高级查询 时候的员工集合
     * @param qo
     * @return
     */
    List<Employee> queryInComIdList(EmployeeQueryObject qo);
}