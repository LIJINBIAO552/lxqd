package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Teacwithclas;
import zqu.lxqd.dao.TeacwithclasMapper;
import zqu.lxqd.service.TeacwithclasService;

import java.util.List;

/**
 * @author ljb
 */
@Service("TeacwithclasService")
public class TeacwithclasServiceImpl implements TeacwithclasService {

    @Autowired
    @Qualifier("teacwithclasMapper")
    private TeacwithclasMapper teacwithclasMapper;

    public void setTeacwithclasMapper(TeacwithclasMapper teacwithclasMapper) {
        this.teacwithclasMapper = teacwithclasMapper;
    }

    @Override
    public int deleteByTwcid(Integer twcid) {
        return teacwithclasMapper.deleteByPrimaryKey(twcid);
    }

    @Override
    public int insertByTeacwithclas(Teacwithclas record) {
        return teacwithclasMapper.insert(record);
    }

    @Override
    public int insertByTeacwithclasSelective(Teacwithclas record) {
        return teacwithclasMapper.insertSelective(record);
    }

    @Override
    public Teacwithclas selectByTwcid(Integer twcid) {
        return teacwithclasMapper.selectByPrimaryKey(twcid);
    }

    @Override
    public List<Teacwithclas> selectByTeacwithclas(Teacwithclas record) {
        return teacwithclasMapper.selectTeacwithclasByTeacwithclas(record);
    }

    @Override
    public int updateByTwcidSelective(Teacwithclas record) {
        return teacwithclasMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByTwcid(Teacwithclas record) {
        return teacwithclasMapper.updateByPrimaryKey(record);
    }
}
