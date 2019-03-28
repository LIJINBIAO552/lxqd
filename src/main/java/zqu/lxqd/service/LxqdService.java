package zqu.lxqd.service;

import zqu.lxqd.bean.Lxqd;

import java.util.List;

/**
 * @author ljb
 */
public interface LxqdService {

    int deleteByLxqdId(String lxqdId);

    int insertByLxqd(Lxqd record);

    int insertByLxqdSelective(Lxqd record);

    Lxqd selectByLxqdId(String lxqdId);

    List<Lxqd> selectAll();

    int updateByLxqdIdSelective(Lxqd record);

    int updateByLxqdId(Lxqd record);
}
