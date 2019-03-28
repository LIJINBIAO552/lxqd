package zqu.lxqd.service;

import zqu.lxqd.bean.Dormmana;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface DormmanaService {
    int deleteDormmanaByStudId(String studId);

    int insertDormmanaByDormmana(Dormmana record);

    int insertDormmanaBySelective(Dormmana record);

    List<Dormmana> simpleSelectDormmanaByStudents(Students students);

    Dormmana selectDormmanaByStudId(String studId);

    List<Dormmana> selectDormmanaByDormmana(Dormmana dormmana);

    List<Dormmana> selectAllDormmana();

    int updateDormmanaByDormmanaSelective(Dormmana record);

    int updateDormmanaByDormmana(Dormmana record);
}