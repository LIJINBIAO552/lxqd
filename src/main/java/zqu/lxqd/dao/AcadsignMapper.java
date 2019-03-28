package zqu.lxqd.dao;

import zqu.lxqd.bean.Acadsign;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface AcadsignMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Acadsign record);

    int insertSelective(Acadsign record);

    List<Acadsign> simpleSelectByStudents(Students students);


    Acadsign selectByPrimaryKey(String studId);

    List<Acadsign> selectByAcadsign(Acadsign record);

    List<Acadsign> selectAllAcadsign();

    int updateByPrimaryKeySelective(Acadsign record);

    int updateByPrimaryKey(Acadsign record);
}