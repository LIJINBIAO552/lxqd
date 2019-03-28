package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Suppcent {
    private String studId;

    private String suppdesc;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private Students students;

    private Admin admin;

    public Suppcent() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId == null ? null : studId.trim();
    }

    public String getSuppdesc() {
        return suppdesc;
    }

    public void setSuppdesc(String suppdesc) {
        this.suppdesc = suppdesc == null ? null : suppdesc.trim();
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public Suppcent(String studId, String suppdesc, String adminId, Date signtime) {
        this.studId = studId;
        this.suppdesc = suppdesc;
        this.adminId = adminId;
        this.signtime = signtime;
    }

//    public Suppcent(String studId, String departId, String major, String sclass, String grade, String education,
//                    String studname, String studsex, String studphone1, String studphone2, String adress,
//                    String familyphone, String suppdesc, String adminname, Date signtime) {
//        this.studId = studId;
//        this.students.setdepartId(departId);
//        this.students.setMajor(major);
//        this.students.setSclass(sclass);
//        this.students.setGrade(grade);
//        this.students.setEducation(education);
//        this.students.setStudname(studname);
//        this.students.setStudsex(studsex);
//        this.students.setStudphone1(studphone1);
//        this.students.setStudphone2(studphone2);
//        this.students.setAdress(adress);
//        this.students.setFamilyphone(familyphone);
//        this.suppdesc = suppdesc;
//        this.admin.setAdminname(adminname);
//        this.signtime = signtime;
//    }


    @Override
    public String toString() {
        return "Suppcent{" +
                "studId='" + studId + '\'' +
                ", suppdesc='" + suppdesc + '\'' +
                ", adminId='" + adminId + '\'' +
                ", signtime=" + signtime +
                ", students=" + students +
                ", admin=" + admin +
                '}';
    }

}