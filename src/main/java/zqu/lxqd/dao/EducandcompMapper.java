package zqu.lxqd.dao;

import zqu.lxqd.bean.Educandcomp;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface EducandcompMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Educandcomp record);

    int insertSelective(Educandcomp record);

    List<Educandcomp> simpleSelectByStudents(Students students);

    Educandcomp selectByPrimaryKey(String studId);

    List<Educandcomp> selectByEducandcomp(Educandcomp record);

    List<Educandcomp> selectAllEducandcomp();

    int updateByPrimaryKeySelective(Educandcomp record);

    int updateByPrimaryKey(Educandcomp record);

}