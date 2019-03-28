package zqu.lxqd.dao;

import zqu.lxqd.bean.Library;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface LibraryMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Library record);

    int insertSelective(Library record);

    List<Library> simpleSelectByStudents(Students students);

    Library selectByPrimaryKey(String studId);

    List<Library> selectByLibrary(Library record);

    List<Library> selectAllLibrary();

    int updateByPrimaryKeySelective(Library record);

    int updateByPrimaryKey(Library record);
}