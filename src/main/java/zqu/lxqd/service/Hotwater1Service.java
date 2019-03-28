package zqu.lxqd.service;

import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Hotwater1;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface Hotwater1Service {

    int deleteByStudId(String studId);

    int insertByHotwater1(Hotwater1 record);

    int insertSelectiveByHotwater1(Hotwater1 record);

    List<Hotwater1> simpleSelectByStudents(Students students);

    Hotwater1 selectByStudId(String studId);

    List<Hotwater1> selectHotwater1ByHotwater1(Hotwater1 record);

    List<Hotwater1> selectAllHotwater1();

    int updateByHotwater1(Hotwater1 record);

    int updateByStuId(Hotwater1 record);

}