package zqu.lxqd.service;

import zqu.lxqd.bean.Library;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface LibraryService {

    int deleteByStudId(String studId);

    int insertByLibrary(Library record);

    int insertSelectiveByLibrary(Library record);

    List<Library> simpleSelectByStudents(Students students);

    Library selectByStudId(String studId);

    List<Library> selectLibraryByLibrary(Library record);

    List<Library> selectAllLibrary();

    int updateByLibrary(Library record);

    int updateByStuId(Library record);

}