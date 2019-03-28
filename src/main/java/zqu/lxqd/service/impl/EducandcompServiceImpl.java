package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Educandcomp;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.EducandcompMapper;
import zqu.lxqd.service.EducAndCompService;

import java.util.List;

/**
 * @author ljb
 */
@Service("EducandcompService")
public class EducandcompServiceImpl implements EducAndCompService {

    @Autowired
    @Qualifier("educandcompMapper")
    private EducandcompMapper educandcompMapper;

    public void setEducandcompMapper(EducandcompMapper educandcompMapper) {
        this.educandcompMapper = educandcompMapper;
    }


    @Override
    public int deleteByStudId(String studId) {
        return educandcompMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByEducandcomp(Educandcomp record) {
        return educandcompMapper.insert(record);
    }

    @Override
    public int insertSelectiveByEducandcomp(Educandcomp record) {
        return educandcompMapper.insertSelective(record);
    }

    @Override
    public List<Educandcomp> simpleSelectByStudents(Students students) {
        return educandcompMapper.simpleSelectByStudents(students);
    }

    @Override
    public Educandcomp selectByStudId(String studId) {
        return educandcompMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Educandcomp> selectEducandcompByEducandcomp(Educandcomp record) {
        return educandcompMapper.selectByEducandcomp(record);
    }

    @Override
    public List<Educandcomp> selectAllEducandcomp() {
        return educandcompMapper.selectAllEducandcomp();
    }

    @Override
    public int updateByEducandcomp(Educandcomp record) {
        return educandcompMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByStuId(Educandcomp record) {
        return educandcompMapper.updateByPrimaryKeySelective(record);
    }
}
