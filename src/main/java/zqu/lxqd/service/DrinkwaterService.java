package zqu.lxqd.service;

import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Students;

import java.util.List;

public interface DrinkwaterService {

    int deleteByStudId(String studId);

    int insertByDrinkwater(Drinkwater record);

    int insertSelectiveByDrinkwater(Drinkwater record);

    List<Drinkwater> simpleSelectByStudents(Students students);

    Drinkwater selectByStudId(String studId);

    List<Drinkwater> selectDrinkwaterByDrinkwater(Drinkwater record);

    List<Drinkwater> selectAllDrinkwater();

    int updateByDrinkwater(Drinkwater record);

    int updateByStuId(Drinkwater record);

}