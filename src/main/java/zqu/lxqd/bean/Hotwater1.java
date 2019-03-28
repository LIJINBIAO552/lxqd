package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Hotwater1 {
    private String studId;

    private String hw1pay;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private String sealer;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sealtime;

    private Students students;

    private Admin admin;

    private Admin admin1;

    public Hotwater1(){

    }

    public Hotwater1(String studId, String nedesc) {
        this.studId = studId;
        this.hw1pay = nedesc;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId == null ? null : studId.trim();
    }

    public String getHw1pay() {
        return hw1pay;
    }

    public void setHw1pay(String hw1pay) {
        this.hw1pay = hw1pay == null ? null : hw1pay.trim();
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

    public Hotwater1(String studId, String hw1pay, String adminId, Date signtime, String sealer, Date sealtime, Students students, Admin admin, Admin admin1) {
        this.studId = studId;
        this.hw1pay = hw1pay;
        this.adminId = adminId;
        this.signtime = signtime;
        this.sealer = sealer;
        this.sealtime = sealtime;
        this.students = students;
        this.admin = admin;
        this.admin1 = admin1;
    }

    public Hotwater1(String studId, String hw1pay, String adminId, Date signtime, String sealer, Date sealtime) {
        this.studId = studId;
        this.hw1pay = hw1pay;
        this.adminId = adminId;
        this.signtime = signtime;
        this.sealer = sealer;
        this.sealtime = sealtime;
    }

    @Override
    public String toString() {
        return "Hotwater1{" +
                "studId='" + studId + '\'' +
                ", hw1pay='" + hw1pay + '\'' +
                ", adminId='" + adminId + '\'' +
                ", signtime=" + signtime +
                ", sealer='" + sealer + '\'' +
                ", sealtime=" + sealtime +
                ", students=" + students +
                ", admin=" + admin +
                ", admin1=" + admin1 +
                '}';
    }
}