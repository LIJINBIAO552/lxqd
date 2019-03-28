package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Hydropower {
    private String studId;

    private String hydrpay;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private Students students;

    private Admin admin;

    public Hydropower(){
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

    public String getHydrpay() {
        return hydrpay;
    }

    public void setHydrpay(String hydrpay) {
        this.hydrpay = hydrpay == null ? null : hydrpay.trim();
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

    public Hydropower(String studId, String hydrpay, String adminId, Date signtime, Students students, Admin admin) {
        this.studId = studId;
        this.hydrpay = hydrpay;
        this.adminId = adminId;
        this.signtime = signtime;
        this.students = students;
        this.admin = admin;
    }

    public Hydropower(String studId, String hydrpay, String adminId, Date signtime) {
        this.studId = studId;
        this.hydrpay = hydrpay;
        this.adminId = adminId;
        this.signtime = signtime;
    }
}