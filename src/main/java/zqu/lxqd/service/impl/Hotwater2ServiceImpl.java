package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Hotwater2;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.Hotwater1Mapper;
import zqu.lxqd.dao.Hotwater2Mapper;
import zqu.lxqd.service.Hotwater2Service;

import java.util.List;

/**
 * @author ljb
 */
@Service("Hotwater2Service")
public class Hotwater2ServiceImpl implements Hotwater2Service {

    @Autowired
    @Qualifier("hotwater2Mapper")
    private Hotwater2Mapper hotwater2Mapper;

    public void setHotwater1Mapper(Hotwater1Mapper hotwater1Mapper) {
        this.hotwater2Mapper = hotwater2Mapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return hotwater2Mapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByHotwater2(Hotwater2 record) {
        return hotwater2Mapper.insert(record);
    }

    @Override
    public int insertSelectiveByHotwater2(Hotwater2 record) {
        return hotwater2Mapper.insertSelective(record);
    }

    @Override
    public List<Hotwater2> simpleSelectByStudents(Students students) {
        return hotwater2Mapper.simpleSelectByStudents(students);
    }

    @Override
    public Hotwater2 selectByStudId(String studId) {
        return hotwater2Mapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Hotwater2> selectHotwater2ByHotwater2(Hotwater2 record) {
        return hotwater2Mapper.selectByHotwater2(record);
    }

    @Override
    public List<Hotwater2> selectAllHotwater2() {
        return hotwater2Mapper.selectAllHotwater2();
    }

    @Override
    public int updateByHotwater2(Hotwater2 record) {
        return hotwater2Mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByStuId(Hotwater2 record) {
        return hotwater2Mapper.updateByPrimaryKey(record);
    }
}
