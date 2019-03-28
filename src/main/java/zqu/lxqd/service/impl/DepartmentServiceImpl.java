package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Admin;
import zqu.lxqd.bean.Department;
import zqu.lxqd.dao.AdminMapper;
import zqu.lxqd.dao.DepartmentMapper;
import zqu.lxqd.service.AdminService;
import zqu.lxqd.service.DepartmentService;

import java.util.List;

/**
 * @author ljb
 */
@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    @Qualifier("departmentMapper")
    private DepartmentMapper departmentMapper;

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public int deleteByDepartId(String departid) {

        return departmentMapper.deleteByPrimaryKey(departid);
    }

    @Override
    public int insertByDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int insertOfSelective(Department department) {
        return departmentMapper.insertSelective(department);
    }

    @Override
    public Department selectByDepartId(String departid) {
        return departmentMapper.selectByPrimaryKey(departid);
    }

    @Override
    public Department selectByDepartmentName(String departname) {
        return departmentMapper.selectByDepartName(departname);
    }

    @Override
    public int updateByDepartIdSelective(Department record) {
        return departmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByDepartId(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }
}
