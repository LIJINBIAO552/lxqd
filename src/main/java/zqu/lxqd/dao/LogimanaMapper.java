package zqu.lxqd.dao;

import zqu.lxqd.bean.Logimana;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface LogimanaMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Logimana record);

    int insertSelective(Logimana record);

    List<Logimana> simpleSelectByStudents(Students students);

    Logimana selectByPrimaryKey(String studId);

    List<Logimana> selectByLogimana(Logimana record);

    List<Logimana> selectAllLogimana();

    int updateByPrimaryKeySelective(Logimana record);

    int updateByPrimaryKey(Logimana record);
}