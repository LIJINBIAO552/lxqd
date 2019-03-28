package zqu.lxqd.dao;

import zqu.lxqd.bean.Students;

import java.util.List;
import java.util.Map;

public interface StudentsMapper {
    int deleteByPrimaryKey(String studid);

    int insert(Students record);

    int insertSelective(Students record);

    Students selectByPrimaryKey(String studid);

    List<Students> selectLikePrimaryKey(String studid);

    List<Students> selectByStudName(String studname);

    List<Students> selectAllStudents();

    List<Students> selectAllStudentsBylimit(Map<String,Integer> map);

    Integer countAllStudents();

    Students selectLXQD(String studid);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);
}