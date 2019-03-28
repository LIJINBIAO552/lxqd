package zqu.lxqd.dao;

import zqu.lxqd.bean.Hydropower;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface HydropowerMapper {

    int deleteByPrimaryKey(String studId);

    int insert(Hydropower record);

    int insertSelective(Hydropower record);

    List<Hydropower> simpleSelectByStudents(Students students);

    Hydropower selectByPrimaryKey(String studId);

    List<Hydropower> selectByHydropower(Hydropower record);

    List<Hydropower> selectAllHydropower();

    int updateByPrimaryKeySelective(Hydropower record);

    int updateByPrimaryKey(Hydropower record);

}