package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Hotwater2 {
    private String studId;

    private String hw2pay;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private String sealer;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sealtime;

    private Students students;

    private Admin admin;

    private Admin admin1;

    public Hotwater2(){

    }
    public Hotwater2(String studId, String nedesc) {
        this.studId = studId;
        this.hw2pay = nedesc;
    }



    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId == null ? null : studId.trim();
    }

    public String getHw2pay() {
        return hw2pay;
    }

    public void setHw2pay(String hw2pay) {
        this.hw2pay = hw2pay == null ? null : hw2pay.trim();
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

    public Hotwater2(String studId, String hw2pay, String adminId, Date signtime, String sealer, Date sealtime) {
        this.studId = studId;
        this.hw2pay = hw2pay;
        this.adminId = adminId;
        this.signtime = signtime;
        this.sealer = sealer;
        this.sealtime = sealtime;
    }

    public Hotwater2(String studId, String hw2pay, String adminId, Date signtime, String sealer, Date sealtime, Students students, Admin admin, Admin admin1) {
        this.studId = studId;
        this.hw2pay = hw2pay;
        this.adminId = adminId;
        this.signtime = signtime;
        this.sealer = sealer;
        this.sealtime = sealtime;
        this.students = students;
        this.admin = admin;
        this.admin1 = admin1;
    }

    @Override
    public String toString() {
        return "Hotwater2{" +
                "studId='" + studId + '\'' +
                ", hw2pay='" + hw2pay + '\'' +
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