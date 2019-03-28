package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Acadsign {
    private String studId;

    private String itemborrdesc;

    private String adminId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signtime;

    private String sealer;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sealtime;

    private Students students;

    private Admin admin;

    private Admin admin1;

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

    public Acadsign(){
    }

    public String getSealer() {
        return sealer;
    }

    public void setSealer(String sealer) {
        this.sealer = sealer;
    }

    public Date getSealtime() {
        return sealtime;
    }

    public void setSealtime(Date sealtime) {
        this.sealtime = sealtime;
    }

    public Admin getAdmin1() {
        return admin1;
    }

    public void setAdmin1(Admin admin1) {
        this.admin1 = admin1;
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

    public String getItemborrdesc() {
        return itemborrdesc;
    }

    public void setItemborrdesc(String itemborrdesc) {
        this.itemborrdesc = itemborrdesc;
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

    public Acadsign(String studId,String itemborrdesc, String adminId, Date signtime) {
        this.studId = studId;
        this.itemborrdesc = itemborrdesc;
        this.adminId = adminId;
        this.signtime = signtime;
    }

    @Override
    public String toString() {
        return "Acadsign{" +
                "studId='" + studId + '\'' +
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