package zqu.lxqd.dao;

import zqu.lxqd.bean.Admin;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    int deleteByPrimaryKey(String adminid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByAdmin(Admin admin);

    Admin selectByPrimaryKey(String adminid);

    List<Admin> selectLikePrimaryKey(String adminid);

    List<Admin> selectByName(String adminname);

    List<Admin> selectAllAdmins();

    List<Admin> selectAllAdminsBylimit(Map<String,Integer> map);

    Integer countAllAdmins();

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKeyWithBLOBs(Admin record);

    int updateByPrimaryKey(Admin record);
}