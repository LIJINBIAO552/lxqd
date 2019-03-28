package zqu.lxqd.service;

import zqu.lxqd.bean.Logimana;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface LogimanaService {

    int deleteByStudId(String studId);

    int insertByLogimana(Logimana record);

    int insertSelectiveByLogimana(Logimana record);

    List<Logimana> simpleSelectByStudents(Students students);

    Logimana selectByStudId(String studId);

    List<Logimana> selectLogimanaByLogimana(Logimana record);

    List<Logimana> selectAllLogimana();

    int updateByStuIdSelective(Logimana record);

    int updateByStuId(Logimana record);

}