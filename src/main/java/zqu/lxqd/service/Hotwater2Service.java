package zqu.lxqd.service;

import zqu.lxqd.bean.Hotwater2;
import zqu.lxqd.bean.Students;

import java.util.List;


public interface Hotwater2Service {

    int deleteByStudId(String studId);

    int insertByHotwater2(Hotwater2 record);

    int insertSelectiveByHotwater2(Hotwater2 record);

    List<Hotwater2> simpleSelectByStudents(Students students);

    Hotwater2 selectByStudId(String studId);

    List<Hotwater2> selectHotwater2ByHotwater2(Hotwater2 record);

    List<Hotwater2> selectAllHotwater2();

    int updateByHotwater2(Hotwater2 record);

    int updateByStuId(Hotwater2 record);

}