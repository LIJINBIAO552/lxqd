package zqu.lxqd.dao;

import zqu.lxqd.bean.Dormmana;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface DormmanaMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Dormmana record);

    int insertSelective(Dormmana record);

    List<Dormmana> simpleSelectByStudents(Students students);

    Dormmana selectByPrimaryKey(String studId);

    List<Dormmana> selectByDormmana(Dormmana dormmana);

    List<Dormmana> selectAllDormmana();

    int updateByPrimaryKeySelective(Dormmana record);

    int updateByPrimaryKey(Dormmana record);
}