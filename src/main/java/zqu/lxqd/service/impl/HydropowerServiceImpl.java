package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Dormmana;
import zqu.lxqd.bean.Hydropower;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.DormmanaMapper;
import zqu.lxqd.dao.HydropowerMapper;
import zqu.lxqd.service.DormmanaService;
import zqu.lxqd.service.HydropowerService;

import java.util.List;

/**
 * @author ljb
 */
@Service("HydropowerService")
public class HydropowerServiceImpl implements HydropowerService {

    @Autowired
    @Qualifier("hydropowerMapper")
    private HydropowerMapper hydropowerMapper;

    public void setHydropowerMapper(HydropowerMapper hydropowerMapper) {
        this.hydropowerMapper = hydropowerMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return hydropowerMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByHydropower(Hydropower record) {
        return hydropowerMapper.insert(record);
    }

    @Override
    public int insertSelectiveByHydropower(Hydropower record) {
        return hydropowerMapper.insertSelective(record);
    }

    @Override
    public List<Hydropower> simpleSelectByStudents(Students students) {
        return hydropowerMapper.simpleSelectByStudents(students);
    }

    @Override
    public Hydropower selectByStudId(String studId) {
        return hydropowerMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Hydropower> selectHydropowerByHydropower(Hydropower record) {
        return hydropowerMapper.selectByHydropower(record);
    }

    @Override
    public List<Hydropower> selectAllHydropower() {
        return hydropowerMapper.selectAllHydropower();
    }

    @Override
    public int updateByHydropower(Hydropower record) {
        return hydropowerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByStuId(Hydropower record) {
        return hydropowerMapper.updateByPrimaryKey(record);
    }
}
