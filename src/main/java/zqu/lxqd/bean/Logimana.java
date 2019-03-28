package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Logimana {
    private String studId;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sealtime;

    private Students students;

    private Admin admin;

    private Hydropower hydropower;

    private Drinkwater drinkwater;

    public Logimana(){
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public Date getSealtime() {
        return sealtime;
    }

    public void setSealtime(Date sealtime) {
        this.sealtime = sealtime;
    }


    public Hydropower getHydropower() {
        return hydropower;
    }

    public void setHydropower(Hydropower hydropower) {
        this.hydropower = hydropower;
    }

    public Drinkwater getDrinkwater() {
        return drinkwater;
    }

    public void setDrinkwater(Drinkwater drinkwater) {
        this.drinkwater = drinkwater;
    }

    public Logimana(String studId, String adminId, Date sealtime, Students students, Admin admin, Hydropower hydropower, Drinkwater drinkwater) {
        this.studId = studId;
        this.adminId = adminId;
        this.sealtime = sealtime;
        this.students = students;
        this.admin = admin;
        this.hydropower = hydropower;
        this.drinkwater = drinkwater;
    }

    public Logimana(String studId, String adminId, Date sealtime, Students students, Admin admin) {
        this.studId = studId;
        this.adminId = adminId;
        this.sealtime = sealtime;
        this.students = students;
        this.admin = admin;
    }

    public Logimana(String studId, String adminId, Date sealtime) {
        this.studId = studId;
        this.adminId = adminId;
        this.sealtime = sealtime;
    }
}