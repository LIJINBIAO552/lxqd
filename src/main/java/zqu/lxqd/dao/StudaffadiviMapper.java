package zqu.lxqd.dao;

import zqu.lxqd.bean.Studaffadivi;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface StudaffadiviMapper {

    int deleteByPrimaryKey(String studId);

    int insert(Studaffadivi record);

    int insertSelective(Studaffadivi record);

    List<Studaffadivi> simpleSelectByStudents(Students students);

    Studaffadivi selectByPrimaryKey(String studId);

    List<Studaffadivi> selectByStudaffadivi(Studaffadivi record);

    List<Studaffadivi> selectAllStudaffadivi();

    int updateByPrimaryKeySelective(Studaffadivi record);

    int updateByPrimaryKey(Studaffadivi record);
}