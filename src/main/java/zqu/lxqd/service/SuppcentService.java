package zqu.lxqd.service;

import zqu.lxqd.bean.Students;
import zqu.lxqd.bean.Suppcent;

import java.util.List;

public interface SuppcentService {
    int deleteByStudId(String studId);

    int insertBySuppCent(Suppcent record);

    int insertBySuppCentSelective(Suppcent record);

    List<Suppcent> selectSuppCentByStudents(Students students);

    Suppcent simpleSelectByStudId(String studId);

    List<Suppcent> selectByStudId(String studId);

    List<Suppcent> selectByStudName(String studname);

    List<Suppcent> selectBydepartmentName(String departname);

    List<Suppcent> selectByStudSex(String studsex);

    List<Suppcent> selectByStudMajor(String major);

    List<Suppcent> selectByStudSclass(String sclass);

    List<Suppcent> selectByStudGrade(String grade);

    List<Suppcent> selectByStudEducation(String education);

    List<Suppcent> selectSuppCentBySuppDesc(String suppdesc);

    List<Suppcent> selectSuppCentByAdminname(String adminname);

    List<Suppcent> selectAllSuppcent();

    int updateBySuppCentSelective(Suppcent record);

    int updateByStudId(Suppcent record);
}