package zqu.lxqd.dao;

import zqu.lxqd.bean.Students;
import zqu.lxqd.bean.Suppcent;

import java.util.List;

public interface SuppcentMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Suppcent record);

    int insertSelective(Suppcent record);

    List<Suppcent> simpleSelectByStudents(Students students);

    Suppcent simpleSelectByPrimaryKey(String studId);

    List<Suppcent> selectByPrimaryKey(String studId);

    List<Suppcent> selectByStudentsName(String studname);

    List<Suppcent> selectByDepartName(String departname);

    List<Suppcent> selectByStudentsSex(String studsex);

    List<Suppcent> selectByStudentsMajor(String major);

    List<Suppcent> selectByStudentsSclass(String sclass);

    List<Suppcent> selectByStudentsGrade(String grade);

    List<Suppcent> selectByStudentsEducation(String education);

    List<Suppcent> selectByAdminname(String adminname);

    List<Suppcent> selectBySuppDesc(String suppdesc);

    List<Suppcent> selectAllSuppCent();

    int updateByPrimaryKeySelective(Suppcent record);

    int updateByPrimaryKey(Suppcent record);
}