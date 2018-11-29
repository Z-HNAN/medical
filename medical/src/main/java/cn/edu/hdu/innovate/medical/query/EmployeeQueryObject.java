package cn.edu.hdu.innovate.medical.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: Z_HNan
 * @Date: 2018/11/29 13:47
 * @Description:
 */
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject{

    private Long comId;     // 公司编号

    private List<Integer> depts;	// comId对应下的所有depts

    private String jobId;   // 工号

    private String realname;    // 姓名
}
