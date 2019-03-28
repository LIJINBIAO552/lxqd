package zqu.lxqd.dao;

import zqu.lxqd.bean.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String departid);

    Department selectByDepartName(String departname);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}