package zqu.lxqd.dao;

import zqu.lxqd.bean.Hotwater1;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface Hotwater1Mapper {

    int deleteByPrimaryKey(String studId);

    int insert(Hotwater1 record);

    int insertSelective(Hotwater1 record);

    List<Hotwater1> simpleSelectByStudents(Students students);

    Hotwater1 selectByPrimaryKey(String studId);

    List<Hotwater1> selectByHotwater1(Hotwater1 record);

    List<Hotwater1> selectAllHotwater1();

    int updateByPrimaryKeySelective(Hotwater1 record);

    int updateByPrimaryKey(Hotwater1 record);


}