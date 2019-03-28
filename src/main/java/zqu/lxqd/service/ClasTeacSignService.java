package zqu.lxqd.service;

import zqu.lxqd.bean.Clasteacsign;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface ClasTeacSignService {

    int deleteByStudId(String studId);

    int insertByClasteacsign(Clasteacsign record);

    int insertClasteacsignByClasteacsign(Clasteacsign record);

    List<Clasteacsign> simpleSelectByStudents(Students students);

    Clasteacsign selectByStudId(String studId);

    List<Clasteacsign> selectClasteacsignByClasteacsign(Clasteacsign record);

    List<Clasteacsign> selectAllClasteacsign();

    int updateByStuIdSelective(Clasteacsign record);

    int updateByStuId(Clasteacsign record);

}