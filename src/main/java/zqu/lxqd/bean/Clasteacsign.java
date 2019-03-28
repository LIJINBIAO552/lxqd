package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Clasteacsign {
    private String studId;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private Students students;

    private Admin admin;

    private Acadsign acadsign;

    private Clasteacsign clasteacsign;

    private Dormmana dormmana;

    private Drinkwater drinkwater;

    private Educandcomp educandcomp;

    private Hotwater1 hotwater1;

    private Hotwater2 hotwater2;

    private Hydropower hydropower;

    private Library library;

    private Logimana logimana;

    private Studaffadivi studaffadivi;

    private Suppcent suppcent;

    private Lxqd lxqd;



    public Clasteacsign(){
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

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }


    public Acadsign getAcadsign() {
        return acadsign;
    }

    public void setAcadsign(Acadsign acadsign) {
        this.acadsign = acadsign;
    }

    public Clasteacsign getClasteacsign() {
        return clasteacsign;
    }

    public void setClasteacsign(Clasteacsign clasteacsign) {
        this.clasteacsign = clasteacsign;
    }

    public Dormmana getDormmana() {
        return dormmana;
    }

    public void setDormmana(Dormmana dormmana) {
        this.dormmana = dormmana;
    }

    public Drinkwater getDrinkwater() {
        return drinkwater;
    }

    public void setDrinkwater(Drinkwater drinkwater) {
        this.drinkwater = drinkwater;
    }

    public Educandcomp getEducandcomp() {
        return educandcomp;
    }

    public void setEducandcomp(Educandcomp educandcomp) {
        this.educandcomp = educandcomp;
    }

    public Hotwater1 getHotwater1() {
        return hotwater1;
    }

    public void setHotwater1(Hotwater1 hotwater1) {
        this.hotwater1 = hotwater1;
    }

    public Hotwater2 getHotwater2() {
        return hotwater2;
    }

    public void setHotwater2(Hotwater2 hotwater2) {
        this.hotwater2 = hotwater2;
    }

    public Hydropower getHydropower() {
        return hydropower;
    }

    public void setHydropower(Hydropower hydropower) {
        this.hydropower = hydropower;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Logimana getLogimana() {
        return logimana;
    }

    public void setLogimana(Logimana logimana) {
        this.logimana = logimana;
    }

    public Studaffadivi getStudaffadivi() {
        return studaffadivi;
    }

    public void setStudaffadivi(Studaffadivi studaffadivi) {
        this.studaffadivi = studaffadivi;
    }

    public Suppcent getSuppcent() {
        return suppcent;
    }

    public void setSuppcent(Suppcent suppcent) {
        this.suppcent = suppcent;
    }

    public Lxqd getLxqd() {
        return lxqd;
    }

    public void setLxqd(Lxqd lxqd) {
        this.lxqd = lxqd;
    }

    public Clasteacsign(String studId, String adminId, Date signtime) {
        this.studId = studId;
        this.adminId = adminId;
        this.signtime = signtime;
    }

    public Clasteacsign(String studId, String adminId, Date signtime, Students students, Admin admin) {
        this.studId = studId;
        this.adminId = adminId;
        this.signtime = signtime;
        this.students = students;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Clasteacsign{" +
                "studId='" + studId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", signtime=" + signtime +
                ", students=" + students +
                ", admin=" + admin +
                '}';
    }
}