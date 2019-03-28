package zqu.lxqd.service;

import zqu.lxqd.bean.Department;

/**
 * @author ljb
 */
public interface DepartmentService {

    int deleteByDepartId(String departid);

    int insertByDepartment(Department department);

    int insertOfSelective(Department department);

    Department selectByDepartId(String departid);

    Department selectByDepartmentName(String departname);

    int updateByDepartIdSelective(Department record);

    int updateByDepartId(Department record);

}
