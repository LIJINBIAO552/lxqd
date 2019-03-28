package zqu.lxqd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zqu.lxqd.bean.Students;
import zqu.lxqd.bean.Suppcent;
import zqu.lxqd.dao.StudentsMapper;
import zqu.lxqd.dao.SuppcentMapper;
import zqu.lxqd.service.SuppcentService;

import java.util.List;

/**
 * @author ljb
 */
@Service("SuppcentService")
public class SuppcentServiceImpl implements SuppcentService {

    @Autowired
    @Qualifier("suppcentMapper")
    private SuppcentMapper suppcentMapper;

    public void setSuppcentMapper(SuppcentMapper suppcentMapper) {
        this.suppcentMapper = suppcentMapper;
    }

    @Override
    public int deleteByStudId(String studId) {
        if (studId != null){
            return suppcentMapper.deleteByPrimaryKey(studId);
        }
        return 0;
    }

    @Override
    public int insertBySuppCent(Suppcent record) {
        if (record != null){
            return suppcentMapper.insert(record);
        }
        return 0;
    }

    @Override
    public int insertBySuppCentSelective(Suppcent record) {
        if (record != null){
            return suppcentMapper.insertSelective(record);
        }
        return 0;
    }

    @Override
    public List<Suppcent> selectSuppCentByStudents(Students students) {
        return suppcentMapper.simpleSelectByStudents(students);
    }

    @Override
    public Suppcent simpleSelectByStudId(String studId) {
        if (studId != null){
            return suppcentMapper.simpleSelectByPrimaryKey(studId);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudId(String studId) {
        if (studId != null){
            return suppcentMapper.selectByPrimaryKey(studId);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudName(String studname) {
        if (studname != null){
            List<Suppcent> suppcents = suppcentMapper.selectByStudentsName(studname);
            if (suppcents.size() > 0){
                return suppcents;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Suppcent> selectBydepartmentName(String departname) {
        if (departname != null){
            List<Suppcent> suppcents = suppcentMapper.selectByDepartName(departname);
            if (suppcents.size() > 0){
                return suppcents;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudSex(String studsex) {
        if (studsex != null){
            return suppcentMapper.selectByStudentsSex(studsex);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudMajor(String major) {
        if (major != null){
            return suppcentMapper.selectByStudentsMajor(major);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudSclass(String sclass) {
        if (sclass != null){
            return suppcentMapper.selectByStudentsSclass(sclass);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudGrade(String grade) {
        if (grade != null){
            return suppcentMapper.selectByStudentsGrade(grade);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectByStudEducation(String education) {
        if (education != null){
            return suppcentMapper.selectByStudentsEducation(education);
        }
        return null;
    }

    @Override
    public List<Suppcent> selectSuppCentBySuppDesc(String suppdesc) {
        return suppcentMapper.selectBySuppDesc(suppdesc);
    }

    @Override
    public List<Suppcent> selectSuppCentByAdminname(String adminname) {
        return suppcentMapper.selectByAdminname(adminname);
    }

    @Override
    public List<Suppcent> selectAllSuppcent() {
        return suppcentMapper.selectAllSuppCent();
    }

    @Override
    public int updateBySuppCentSelective(Suppcent record) {
        if (record != null){
            return suppcentMapper.updateByPrimaryKeySelective(record);
        }
        return 0;
    }

    @Override
    public int updateByStudId(Suppcent record) {
        if (record != null){
            return suppcentMapper.updateByPrimaryKey(record);
        }
        return 0;
    }
}
