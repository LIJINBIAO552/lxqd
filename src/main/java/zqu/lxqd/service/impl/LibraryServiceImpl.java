package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Library;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.LibraryMapper;
import zqu.lxqd.service.LibraryService;

import java.util.List;

/**
 * @author ljb
 */
@Service("LibraryService")
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    @Qualifier("libraryMapper")
    private LibraryMapper libraryMapper;


    public void setLibraryMapper(LibraryMapper libraryMapper) {
        this.libraryMapper = libraryMapper;
    }


    @Override
    public int deleteByStudId(String studId) {
        return libraryMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public List<Library> simpleSelectByStudents(Students students) {
        return libraryMapper.simpleSelectByStudents(students);
    }

    @Override
    public Library selectByStudId(String studId) {
        return libraryMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Library> selectAllLibrary() {
        return libraryMapper.selectAllLibrary();
    }

    @Override
    public int updateByStuId(Library record) {
        return libraryMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByLibrary(Library record) {
        return libraryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Library> selectLibraryByLibrary(Library record) {
        return libraryMapper.selectByLibrary(record);
    }

    @Override
    public int insertSelectiveByLibrary(Library record) {
        return libraryMapper.insertSelective(record);
    }

    @Override
    public int insertByLibrary(Library record) {
        return libraryMapper.insert(record);
    }
}
