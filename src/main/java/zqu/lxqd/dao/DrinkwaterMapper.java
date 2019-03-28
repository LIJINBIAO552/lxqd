package zqu.lxqd.dao;

import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface DrinkwaterMapper {
    int deleteByPrimaryKey(String studId);

    int insert(Drinkwater record);

    int insertSelective(Drinkwater record);

    List<Drinkwater> simpleSelectByStudents(Students students);

    Drinkwater selectByPrimaryKey(String studId);

    List<Drinkwater> selectByDrinkwater(Drinkwater record);

    List<Drinkwater> selectAllDrinkwater();

    int updateByPrimaryKeySelective(Drinkwater record);

    int updateByPrimaryKey(Drinkwater record);
}