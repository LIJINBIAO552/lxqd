package zqu.lxqd.dao;

import zqu.lxqd.bean.Lxqd;

import java.util.List;

public interface LxqdMapper {
    int deleteByPrimaryKey(String lxqdId);

    int insert(Lxqd record);

    int insertSelective(Lxqd record);

    Lxqd selectByPrimaryKey(String lxqdId);

    List<Lxqd> select();

    int updateByPrimaryKeySelective(Lxqd record);

    int updateByPrimaryKey(Lxqd record);
}