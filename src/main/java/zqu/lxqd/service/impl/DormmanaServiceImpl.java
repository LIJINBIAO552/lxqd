package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Dormmana;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.DormmanaMapper;
import zqu.lxqd.service.DormmanaService;

import java.util.List;

/**
 * @author ljb
 */
@Service("DormmanaService")
public class DormmanaServiceImpl implements DormmanaService {

    @Autowired
    @Qualifier("dormmanaMapper")
    private DormmanaMapper dormmanaMapper;

    public void setDormmanaMapper(DormmanaMapper dormmanaMapper) {
        this.dormmanaMapper = dormmanaMapper;
    }

    @Override
    public int deleteDormmanaByStudId(String studId) {
        return dormmanaMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertDormmanaByDormmana(Dormmana record) {
        return dormmanaMapper.insert(record);
    }

    @Override
    public int insertDormmanaBySelective(Dormmana record) {
        return dormmanaMapper.insertSelective(record);
    }

    @Override
    public List<Dormmana> simpleSelectDormmanaByStudents(Students students) {
        return dormmanaMapper.simpleSelectByStudents(students);
    }

    @Override
    public Dormmana selectDormmanaByStudId(String studId) {
        return dormmanaMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Dormmana> selectDormmanaByDormmana(Dormmana dormmana) {
        return dormmanaMapper.selectByDormmana(dormmana);
    }

    @Override
    public List<Dormmana> selectAllDormmana() {
        return dormmanaMapper.selectAllDormmana();
    }

    @Override
    public int updateDormmanaByDormmanaSelective(Dormmana record) {
        return dormmanaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateDormmanaByDormmana(Dormmana record) {
        return dormmanaMapper.updateByPrimaryKey(record);
    }
}
