package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Hotwater1;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.Hotwater1Mapper;
import zqu.lxqd.service.Hotwater1Service;

import java.util.List;

/**
 * @author ljb
 */
@Service("Hotwater1Service")
public class Hotwater1ServiceImpl implements Hotwater1Service {

    @Autowired
    @Qualifier("hotwater1Mapper")
    private Hotwater1Mapper hotwater1Mapper;

    public void setHotwater1Mapper(Hotwater1Mapper hotwater1Mapper) {
        this.hotwater1Mapper = hotwater1Mapper;
    }


    @Override
    public int deleteByStudId(String studId) {
        return hotwater1Mapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByHotwater1(Hotwater1 record) {
        return hotwater1Mapper.insert(record);
    }

    @Override
    public int insertSelectiveByHotwater1(Hotwater1 record) {
        return hotwater1Mapper.insertSelective(record);
    }

    @Override
    public List<Hotwater1> simpleSelectByStudents(Students students) {
        return hotwater1Mapper.simpleSelectByStudents(students);
    }

    @Override
    public Hotwater1 selectByStudId(String studId) {
        return hotwater1Mapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Hotwater1> selectHotwater1ByHotwater1(Hotwater1 record) {
        return hotwater1Mapper.selectByHotwater1(record);
    }

    @Override
    public List<Hotwater1> selectAllHotwater1() {
        return hotwater1Mapper.selectAllHotwater1();
    }

    @Override
    public int updateByHotwater1(Hotwater1 record) {
        return hotwater1Mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByStuId(Hotwater1 record) {
        return hotwater1Mapper.updateByPrimaryKeySelective(record);
    }
}
