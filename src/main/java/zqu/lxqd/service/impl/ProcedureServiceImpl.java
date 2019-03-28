package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Procedure;
import zqu.lxqd.dao.ProcedureMapper;
import zqu.lxqd.service.ProcedureService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */
@Service("ProcedureService")
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    @Qualifier("procedureMapper")
    private ProcedureMapper procedureMapper;

    public void setProcedureMapper(ProcedureMapper procedureMapper) {
        this.procedureMapper = procedureMapper;
    }

    @Override
    public int deleteByProcedureId(Integer procedureId) {
        return procedureMapper.deleteByPrimaryKey(procedureId);
    }

    @Override
    public int insertByProcedure(Procedure record) {
        return procedureMapper.insert(record);
    }

    @Override
    public int insertByProcedureSelective(Procedure record) {
        return procedureMapper.insertSelective(record);
    }

    @Override
    public Procedure selectByProcedureId(Integer procedureId) {
        return procedureMapper.selectByPrimaryKey(procedureId);
    }

    @Override
    public List<Procedure> selectProcedureByLimitYear(String year) {
        return procedureMapper.selectByLimitYear(year);
    }

    @Override
    public List<Procedure> selectProcedureByStartAndEnd(Map<String, Date> map) {
        return procedureMapper.selectByStartAndEnd(map);
    }

    @Override
    public List<Procedure> selectAll() {
        return procedureMapper.select();
    }

    @Override
    public int updateByProcedureIdSelective(Procedure record) {
        return procedureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByProcedureId(Procedure record) {
        return procedureMapper.updateByPrimaryKey(record);
    }
}
