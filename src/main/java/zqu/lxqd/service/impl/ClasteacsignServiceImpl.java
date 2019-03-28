package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Clasteacsign;
import zqu.lxqd.bean.Studaffadivi;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.ClasteacsignMapper;
import zqu.lxqd.dao.StudaffadiviMapper;
import zqu.lxqd.service.ClasTeacSignService;
import zqu.lxqd.service.StudaffadiviService;

import java.util.List;

/**
 * @author ljb
 */
@Service("ClasteacsignService")
public class ClasteacsignServiceImpl implements ClasTeacSignService {

    @Autowired
    @Qualifier("clasteacsignMapper")
    private ClasteacsignMapper clasteacsignMapper;

    public void setClasteacsignMapper(ClasteacsignMapper clasteacsignMapper) {
        this.clasteacsignMapper = clasteacsignMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return clasteacsignMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByClasteacsign(Clasteacsign record) {
        return clasteacsignMapper.insert(record);
    }

    @Override
    public int insertClasteacsignByClasteacsign(Clasteacsign record) {
        return clasteacsignMapper.insertSelective(record);
    }

    @Override
    public List<Clasteacsign> simpleSelectByStudents(Students students) {
        return clasteacsignMapper.simpleSelectByStudents(students);
    }

    @Override
    public Clasteacsign selectByStudId(String studId) {
        return clasteacsignMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Clasteacsign> selectClasteacsignByClasteacsign(Clasteacsign record) {
        return clasteacsignMapper.selectByClasteacsign(record);
    }

    @Override
    public List<Clasteacsign> selectAllClasteacsign() {
        return clasteacsignMapper.selectAllClasteacsign();
    }

    @Override
    public int updateByStuIdSelective(Clasteacsign record) {
        return clasteacsignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByStuId(Clasteacsign record) {
        return clasteacsignMapper.updateByPrimaryKey(record);
    }
}
