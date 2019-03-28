package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Dormmana;
import zqu.lxqd.bean.Studaffadivi;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.DormmanaMapper;
import zqu.lxqd.dao.StudaffadiviMapper;
import zqu.lxqd.service.DormmanaService;
import zqu.lxqd.service.StudaffadiviService;

import java.util.List;

/**
 * @author ljb
 */
@Service("StudaffadiviService")
public class StudaffadiviServiceImpl implements StudaffadiviService {

    @Autowired
    @Qualifier("studaffadiviMapper")
    private StudaffadiviMapper studaffadiviMapper;

    public void setStudaffadiviMapper(StudaffadiviMapper studaffadiviMapper) {
        this.studaffadiviMapper = studaffadiviMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return studaffadiviMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByStudaffadivi(Studaffadivi record) {
        return studaffadiviMapper.insert(record);
    }

    @Override
    public int insertSelectiveByStudaffadivi(Studaffadivi record) {
        return studaffadiviMapper.insertSelective(record);
    }

    @Override
    public List<Studaffadivi> simpleSelectByStudents(Students students) {
        return studaffadiviMapper.simpleSelectByStudents(students);
    }

    @Override
    public Studaffadivi selectByStudId(String studId) {
        return studaffadiviMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Studaffadivi> selectStudaffadiviByStudaffadivi(Studaffadivi record) {
        return studaffadiviMapper.selectByStudaffadivi(record);
    }

    @Override
    public List<Studaffadivi> selectAllStudaffadivi() {
        return studaffadiviMapper.selectAllStudaffadivi();
    }

    @Override
    public int updateByStuIdSelective(Studaffadivi record) {
        return studaffadiviMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByStuId(Studaffadivi record) {
        return studaffadiviMapper.updateByPrimaryKey(record);
    }
}
