package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Acadsign;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.AcadsignMapper;
import zqu.lxqd.service.AcadsignService;

import java.util.List;

/**
 * @author ljb
 */
@Service("AcadsignService")
public class AcadsignServiceImpl implements AcadsignService {

    @Autowired
    @Qualifier("acadsignMapper")
    private AcadsignMapper acadsignMapper;

    public void setacadsignMapper(AcadsignMapper acadsignMapper) {
        this.acadsignMapper = acadsignMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return acadsignMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByAcadsign(Acadsign record) {
        return acadsignMapper.insert(record);
    }

    @Override
    public int insertSelectiveByAcadsign(Acadsign record) {
        return acadsignMapper.insertSelective(record);
    }

    @Override
    public List<Acadsign> simpleSelectByStudents(Students students) {
        return acadsignMapper.simpleSelectByStudents(students);
    }

    @Override
    public Acadsign selectByStudId(String studId) {
        return acadsignMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Acadsign> selectAcadsignByAcadsign(Acadsign record) {
        return acadsignMapper.selectByAcadsign(record);
    }

    @Override
    public List<Acadsign> selectAllAcadsign() {
        return acadsignMapper.selectAllAcadsign();
    }

    @Override
    public int updateByAcadsign(Acadsign record) {
        return acadsignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByStuId(Acadsign record) {
        return acadsignMapper.updateByPrimaryKey(record);
    }
}
