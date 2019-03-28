package zqu.lxqd.service;

import zqu.lxqd.bean.Studaffadivi;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface StudaffadiviService {

    int deleteByStudId(String studId);

    int insertByStudaffadivi(Studaffadivi record);

    int insertSelectiveByStudaffadivi(Studaffadivi record);

    List<Studaffadivi> simpleSelectByStudents(Students students);

    Studaffadivi selectByStudId(String studId);

    List<Studaffadivi> selectStudaffadiviByStudaffadivi(Studaffadivi record);

    List<Studaffadivi> selectAllStudaffadivi();

    int updateByStuIdSelective(Studaffadivi record);

    int updateByStuId(Studaffadivi record);

}