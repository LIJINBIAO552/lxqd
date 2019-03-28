package zqu.lxqd.dao;

import zqu.lxqd.bean.Procedure;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProcedureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Procedure record);

    int insertSelective(Procedure record);

    Procedure selectByPrimaryKey(Integer id);

    List<Procedure> selectByLimitYear(String year);

    List<Procedure> selectByStartAndEnd(Map<String, Date> map);

    List<Procedure> select();

    int updateByPrimaryKeySelective(Procedure record);

    int updateByPrimaryKey(Procedure record);
}