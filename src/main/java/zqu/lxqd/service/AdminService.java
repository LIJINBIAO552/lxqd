package zqu.lxqd.service;

import zqu.lxqd.bean.Admin;

import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */

public interface AdminService {

    int deleteByAdminId(String adminid);

    int insertByAdmin(Admin record);

    int insertOfSelective(Admin record);

    List<Admin> selectAdminByAdmin(Admin admin);

    Admin selectByAdminId(String adminid);

    List<Admin> selectLikeAdminId(String adminid);

    List<Admin> selectByAdminName(String adminname);

    List<Admin> selectAllAdmin();

    List<Admin> selectAllAdminBylimits(Map<String,Integer> map);

    Integer countAllAdmin();

    int updateByAdminIdSelective(Admin record);

    int updateByAdminIdWithBLOBs(Admin record);

    int updateByAdminId(Admin record);

}
