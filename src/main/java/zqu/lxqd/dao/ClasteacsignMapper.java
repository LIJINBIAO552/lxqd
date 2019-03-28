package zqu.lxqd.dao;

import zqu.lxqd.bean.Clasteacsign;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface ClasteacsignMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Clasteacsign record);

    int insertSelective(Clasteacsign record);

    List<Clasteacsign> simpleSelectByStudents(Students students);

    Clasteacsign selectByPrimaryKey(String studId);

    List<Clasteacsign> selectByClasteacsign(Clasteacsign record);

    List<Clasteacsign> selectAllClasteacsign();

    int updateByPrimaryKeySelective(Clasteacsign record);

    int updateByPrimaryKey(Clasteacsign record);
}