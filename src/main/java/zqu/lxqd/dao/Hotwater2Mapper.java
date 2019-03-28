package zqu.lxqd.dao;

import zqu.lxqd.bean.Hotwater2;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface Hotwater2Mapper {
    int deleteByPrimaryKey(String studId);

    int insert(Hotwater2 record);

    int insertSelective(Hotwater2 record);

    List<Hotwater2> simpleSelectByStudents(Students students);

    Hotwater2 selectByPrimaryKey(String studId);

    List<Hotwater2> selectByHotwater2(Hotwater2 record);

    List<Hotwater2> selectAllHotwater2();

    int updateByPrimaryKeySelective(Hotwater2 record);

    int updateByPrimaryKey(Hotwater2 record);
}