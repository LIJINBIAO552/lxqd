package zqu.lxqd.service;


import zqu.lxqd.bean.Procedure;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */
public interface ProcedureService {

    int deleteByProcedureId(Integer id);

    int insertByProcedure(Procedure record);

    int insertByProcedureSelective(Procedure record);

    Procedure selectByProcedureId(Integer id);

    List<Procedure> selectProcedureByLimitYear(String year);

    List<Procedure> selectProcedureByStartAndEnd(Map<String, Date> map);

    List<Procedure> selectAll();

    int updateByProcedureIdSelective(Procedure record);

    int updateByProcedureId(Procedure record);
}
