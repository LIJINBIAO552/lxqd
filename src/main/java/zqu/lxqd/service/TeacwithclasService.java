package zqu.lxqd.service;

import zqu.lxqd.bean.Teacwithclas;

import java.util.List;

/**
 * @author ljb
 */
public interface TeacwithclasService {

    int deleteByTwcid(Integer twcid);

    int insertByTeacwithclas(Teacwithclas record);

    int insertByTeacwithclasSelective(Teacwithclas record);

    Teacwithclas selectByTwcid(Integer twcid);

    List<Teacwithclas> selectByTeacwithclas(Teacwithclas record);

    int updateByTwcidSelective(Teacwithclas record);

    int updateByTwcid(Teacwithclas record);
}
