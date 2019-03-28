package zqu.lxqd.service;

import zqu.lxqd.bean.Educandcomp;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface EducAndCompService {

    int deleteByStudId(String studId);

    int insertByEducandcomp(Educandcomp record);

    int insertSelectiveByEducandcomp(Educandcomp record);

    List<Educandcomp> simpleSelectByStudents(Students students);

    Educandcomp selectByStudId(String studId);

    List<Educandcomp> selectEducandcompByEducandcomp(Educandcomp record);

    List<Educandcomp> selectAllEducandcomp();

    int updateByEducandcomp(Educandcomp record);

    int updateByStuId(Educandcomp record);

}