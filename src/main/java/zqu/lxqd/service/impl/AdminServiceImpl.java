package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Admin;
import zqu.lxqd.dao.AdminMapper;
import zqu.lxqd.service.AdminService;

import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    @Qualifier("adminMapper")
    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public int deleteByAdminId(String adminid) {

        if (adminid != null) {
            return adminMapper.deleteByPrimaryKey(adminid);
        }
        return 0;
    }

    @Override
    public int insertByAdmin(Admin record) {
        if (record != null) {
            return adminMapper.insert(record);
        }
        return 0;
    }

    @Override
    public int insertOfSelective(Admin record) {
        if (record != null) {
            return adminMapper.insertSelective(record);
        }
        return 0;
    }

    @Override
    public List<Admin> selectAdminByAdmin(Admin admin) {
        return adminMapper.selectByAdmin(admin);
    }

    @Override
    public Admin selectByAdminId(String adminid) {

        return  adminMapper.selectByPrimaryKey(adminid);
    }

    @Override
    public List<Admin> selectLikeAdminId(String adminid) {

        return  adminMapper.selectLikePrimaryKey(adminid);
    }

    @Override
    public List<Admin> selectByAdminName(String adminname) {
        if (adminname != null) {
            return adminMapper.selectByName(adminname);
        }
        return null;
    }

    @Override
    public List<Admin> selectAllAdmin() {

        return adminMapper.selectAllAdmins();

    }

    @Override
    public List<Admin> selectAllAdminBylimits(Map<String,Integer> map) {
        return adminMapper.selectAllAdminsBylimit(map);
    }

    @Override
    public Integer countAllAdmin() {
        return adminMapper.countAllAdmins();
    }

    @Override
    public int updateByAdminIdSelective(Admin record) {
        if (record != null) {
            return adminMapper.updateByPrimaryKeySelective(record);
        }
        return 0;
    }

    @Override
    public int updateByAdminIdWithBLOBs(Admin record) {
        if (record != null) {
            return adminMapper.updateByPrimaryKeyWithBLOBs(record);
        }
        return 0;
    }

    @Override
    public int updateByAdminId(Admin record) {
        if (record != null) {
            return adminMapper.updateByPrimaryKey(record);
        }
        return 0;
    }
}
