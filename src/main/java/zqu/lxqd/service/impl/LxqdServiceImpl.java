package zqu.lxqd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Lxqd;
import zqu.lxqd.dao.LxqdMapper;
import zqu.lxqd.service.LxqdService;

import java.util.List;

/**
 * @author ljb
 */
@Service("LxqdService")
public class LxqdServiceImpl implements LxqdService {

    @Autowired
    @Qualifier("lxqdMapper")
    private LxqdMapper lxqdMapper;

    public void setLxqdMapper(LxqdMapper lxqdMapper) {
        this.lxqdMapper = lxqdMapper;
    }

    @Override
    public int deleteByLxqdId(String lxqdId) {
        return lxqdMapper.deleteByPrimaryKey(lxqdId);
    }

    @Override
    public int insertByLxqd(Lxqd record) {
        return lxqdMapper.insert(record);
    }

    @Override
    public int insertByLxqdSelective(Lxqd record) {
        return lxqdMapper.insertSelective(record);
    }

    @Override
    public Lxqd selectByLxqdId(String lxqdId) {
        return lxqdMapper.selectByPrimaryKey(lxqdId);
    }

    @Override
    public List<Lxqd> selectAll() {
        return lxqdMapper.select();
    }

    @Override
    public int updateByLxqdIdSelective(Lxqd record) {
        return lxqdMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByLxqdId(Lxqd record) {
        return lxqdMapper.updateByPrimaryKey(record);
    }
}
