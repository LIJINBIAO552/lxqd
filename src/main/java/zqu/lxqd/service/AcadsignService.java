package zqu.lxqd.service;

import zqu.lxqd.bean.Acadsign;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface AcadsignService {

    int deleteByStudId(String studId);

    int insertByAcadsign(Acadsign record);

    int insertSelectiveByAcadsign(Acadsign record);

    List<Acadsign> simpleSelectByStudents(Students students);

    Acadsign selectByStudId(String studId);

    List<Acadsign> selectAcadsignByAcadsign(Acadsign record);

    List<Acadsign> selectAllAcadsign();

    int updateByAcadsign(Acadsign record);

    int updateByStuId(Acadsign record);

}