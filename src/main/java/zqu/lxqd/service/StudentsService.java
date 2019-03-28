package zqu.lxqd.service;

import zqu.lxqd.bean.Students;

import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */
public interface StudentsService {

    int deleteByStudId(String studid);

    int insertByStudents(Students record);

    int insertSelectiveByStudents(Students record);

    Students selectByStudId(String studid);

    List<Students> selectLikeStudId(String studid);

    List<Students> selectByStuName(String studname);

    List<Students> selectAllStudent();

    List<Students> selectAllStudBylimits(Map<String,Integer> map);

    Integer countAllStud();

    int updateByStuIdSelective(Students record);

    int updateByStuId(Students record);
}
