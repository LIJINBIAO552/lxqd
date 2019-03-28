package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Hydropower;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.DrinkwaterMapper;
import zqu.lxqd.dao.HydropowerMapper;
import zqu.lxqd.service.DrinkwaterService;

import java.util.List;

/**
 * @author ljb
 */
@Service("DrinkwaterService")
public class DrinkwaterServiceImpl implements DrinkwaterService {

    @Autowired
    @Qualifier("drinkwaterMapper")
    private DrinkwaterMapper drinkwaterMapper;

    public void setDrinkwaterMapper(DrinkwaterMapper drinkwaterMapper) {
        this.drinkwaterMapper = drinkwaterMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return drinkwaterMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByDrinkwater(Drinkwater record) {
        return drinkwaterMapper.insert(record);
    }

    @Override
    public int insertSelectiveByDrinkwater(Drinkwater record) {
        return drinkwaterMapper.insertSelective(record);
    }

    @Override
    public List<Drinkwater> simpleSelectByStudents(Students students) {
        return drinkwaterMapper.simpleSelectByStudents(students);
    }

    @Override
    public Drinkwater selectByStudId(String studId) {
        return drinkwaterMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Drinkwater> selectDrinkwaterByDrinkwater(Drinkwater record) {
        return drinkwaterMapper.selectByDrinkwater(record);
    }

    @Override
    public List<Drinkwater> selectAllDrinkwater() {
        return drinkwaterMapper.selectAllDrinkwater();
    }

    @Override
    public int updateByDrinkwater(Drinkwater record) {
        return drinkwaterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByStuId(Drinkwater record) {
        return drinkwaterMapper.updateByPrimaryKey(record);
    }
}
