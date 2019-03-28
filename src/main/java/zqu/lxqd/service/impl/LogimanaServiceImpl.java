package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Logimana;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.LogimanaMapper;
import zqu.lxqd.service.LogimanaService;

import java.util.List;

/**
 * @author ljb
 */
@Service("LogimanaService")
public class LogimanaServiceImpl implements LogimanaService {

    @Autowired
    @Qualifier("logimanaMapper")
    private LogimanaMapper LogimanaMapper;

    public void setLogimanaMapper(LogimanaMapper LogimanaMapper) {
        this.LogimanaMapper = LogimanaMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        return LogimanaMapper.deleteByPrimaryKey(studId);
    }

    @Override
    public int insertByLogimana(Logimana record) {
        return LogimanaMapper.insert(record);
    }

    @Override
    public int insertSelectiveByLogimana(Logimana record) {
        return LogimanaMapper.insertSelective(record);
    }

    @Override
    public List<Logimana> simpleSelectByStudents(Students students) {
        return LogimanaMapper.simpleSelectByStudents(students);
    }

    @Override
    public Logimana selectByStudId(String studId) {
        return LogimanaMapper.selectByPrimaryKey(studId);
    }

    @Override
    public List<Logimana> selectLogimanaByLogimana(Logimana record) {
        return LogimanaMapper.selectByLogimana(record);
    }

    @Override
    public List<Logimana> selectAllLogimana() {
        return LogimanaMapper.selectAllLogimana();
    }

    @Override
    public int updateByStuIdSelective(Logimana record) {
        return LogimanaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByStuId(Logimana record) {
        return LogimanaMapper.updateByPrimaryKey(record);
    }
}
