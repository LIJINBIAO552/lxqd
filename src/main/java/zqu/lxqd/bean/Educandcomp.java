package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;


import java.util.Date;

public class Educandcomp {
    private String studId;

    private String rentdesc;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private String sealer;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sealtime;

    private Students students;

    private Admin admin;

    private Admin admin1;

    public Educandcomp(){
    }

    public Educandcomp(String studId, String nedesc) {
        this.studId = studId;
        this.rentdesc = nedesc;
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

    public Admin getAdmin1() {
        return admin1;
    }

    public void setAdmin1(Admin admin1) {
        this.admin1 = admin1;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId == null ? null : studId.trim();
    }

    public String getRentdesc() {
        return rentdesc;
    }

    public void setRentdesc(String rentdesc) {
        this.rentdesc = rentdesc == null ? null : rentdesc.trim();
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

    public String getSealer() {
        return sealer;
    }

    public void setSealer(String sealer) {
        this.sealer = sealer == null ? null : sealer.trim();
    }

    public Date getSealtime() {
        return sealtime;
    }

    public void setSealtime(Date sealtime) {
        this.sealtime = sealtime;
    }

    public Educandcomp(String studId, String rentdesc, String adminId, Date signtime, String sealer, Date sealtime) {
        this.studId = studId;
        this.rentdesc = rentdesc;
        this.adminId = adminId;
        this.signtime = signtime;
        this.sealer = sealer;
        this.sealtime = sealtime;
    }
}