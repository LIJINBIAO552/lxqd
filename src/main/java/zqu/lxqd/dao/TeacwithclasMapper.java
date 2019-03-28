package zqu.lxqd.dao;

import zqu.lxqd.bean.Teacwithclas;

import java.util.List;

public interface TeacwithclasMapper {
    int deleteByPrimaryKey(Integer twcid);

    int insert(Teacwithclas record);

    int insertSelective(Teacwithclas record);

    Teacwithclas selectByPrimaryKey(Integer twcid);

    List<Teacwithclas> selectTeacwithclasByTeacwithclas(Teacwithclas record);

    int updateByPrimaryKeySelective(Teacwithclas record);

    int updateByPrimaryKey(Teacwithclas record);
}