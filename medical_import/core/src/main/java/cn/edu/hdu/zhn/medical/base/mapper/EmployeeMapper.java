package cn.edu.hdu.zhn.medical.base.mapper;

import cn.edu.hdu.zhn.medical.base.domain.Employee;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	Long getIdByJobIdOrRealname(@Param("jobId")String jobId, @Param("realname")String realname);
}