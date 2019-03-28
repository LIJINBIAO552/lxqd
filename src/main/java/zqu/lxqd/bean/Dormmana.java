package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Dormmana {
    private String studId;

    private String dormdesc;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private Students students;

    private Admin admin;

    public Dormmana(){
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId == null ? null : studId.trim();
    }

    public String getDormdesc() {
        return dormdesc;
    }

    public void setDormdesc(String dormdesc) {
        this.dormdesc = dormdesc == null ? null : dormdesc.trim();
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

    public Dormmana(String studId, String dormdesc, String adminId, Date signtime) {
        this.studId = studId;
        this.dormdesc = dormdesc;
        this.adminId = adminId;
        this.signtime = signtime;
    }
}