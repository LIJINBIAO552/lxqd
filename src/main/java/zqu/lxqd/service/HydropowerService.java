package zqu.lxqd.service;

import zqu.lxqd.bean.Hydropower;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface HydropowerService {

    int deleteByStudId(String studId);

    int insertByHydropower(Hydropower record);

    int insertSelectiveByHydropower(Hydropower record);

    List<Hydropower> simpleSelectByStudents(Students students);

    Hydropower selectByStudId(String studId);

    List<Hydropower> selectHydropowerByHydropower(Hydropower record);

    List<Hydropower> selectAllHydropower();

    int updateByHydropower(Hydropower record);

    int updateByStuId(Hydropower record);

}