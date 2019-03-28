package zqu.lxqd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Students;
import zqu.lxqd.dao.StudentsMapper;
import zqu.lxqd.service.StudentsService;

import java.util.List;
import java.util.Map;

/**
 * @author ljb
 */
@Service("StudentsService")
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    @Qualifier("studentsMapper")
    private StudentsMapper studentsMapper;

    public void setStudentsMapper(StudentsMapper studentsMapper) {
        this.studentsMapper = studentsMapper;
    }

    @Override
    public int deleteByStudId(String studid) {
        if (studid != null) {
            return studentsMapper.deleteByPrimaryKey(studid);
        }
        return 0;
    }

    @Override
    public int insertByStudents(Students record) {
        if (record != null) {
            return studentsMapper.insert(record);
        }
        return 0;
    }

    @Override
    public int insertSelectiveByStudents(Students record) {
        if (record != null) {
            return studentsMapper.insertSelective(record);
        }
        return 0;
    }

    @Override
    public Students selectByStudId(String studid) {
        if (studid != null) {
            return studentsMapper.selectByPrimaryKey(studid);
        }
        return null;
    }

    @Override
    public List<Students> selectLikeStudId(String studid) {
        return studentsMapper.selectLikePrimaryKey(studid);
    }

    @Override
    public List<Students> selectByStuName(String studname) {
        if (studname != null) {
            return studentsMapper.selectByStudName(studname);
        }
        return null;
    }

    @Override
    public List<Students> selectAllStudent() {

        return studentsMapper.selectAllStudents();

    }

    @Override
    public List<Students> selectAllStudBylimits(Map<String, Integer> map) {
        return studentsMapper.selectAllStudentsBylimit(map);
    }

    @Override
    public Integer countAllStud() {
        return studentsMapper.countAllStudents();
    }

    @Override
    public int updateByStuIdSelective(Students record) {
        if (record != null) {
            return studentsMapper.updateByPrimaryKeySelective(record);
        }
        return 0;
    }

    @Override
    public int updateByStuId(Students record) {
        if (record != null) {
            return studentsMapper.updateByPrimaryKey(record);
        }
        return 0;
    }
}
