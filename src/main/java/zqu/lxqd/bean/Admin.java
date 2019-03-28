package zqu.lxqd.bean;

import java.util.List;

public class Admin {
    private String adminid;

    private String adminpass;

    private String adminname;

    private String adminsex;

    private String departid;

    private String adminiden;

    private String adminphone;

    private String adminmail;

    private String adminphoto;

    private List<Students> studentsList;

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Students> studentsList) {
        this.studentsList = studentsList;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid == null ? null : adminid.trim();
    }

    public String getAdminpass() {
        return adminpass;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass == null ? null : adminpass.trim();
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminsex() {
        return adminsex;
    }

    public void setAdminsex(String adminsex) {
        this.adminsex = adminsex == null ? null : adminsex.trim();
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid == null ? null : departid.trim();
    }

    public String getAdminiden() {
        return adminiden;
    }

    public void setAdminiden(String adminiden) {
        this.adminiden = adminiden == null ? null : adminiden.trim();
    }

    public String getAdminphone() {
        return adminphone;
    }

    public void setAdminphone(String adminphone) {
        this.adminphone = adminphone == null ? null : adminphone.trim();
    }

    public String getAdminmail() {
        return adminmail;
    }

    public void setAdminmail(String adminmail) {
        this.adminmail = adminmail == null ? null : adminmail.trim();
    }

    public String getAdminphoto() {
        return adminphoto;
    }

    public void setAdminphoto(String adminphoto) {
        this.adminphoto = adminphoto == null ? null : adminphoto.trim();
    }

    public Admin() {
    }
    
    public Admin(String adminid, String adminpass, String adminname, String adminsex,String departid,
            Department department,String adminiden, String adminphone, String adminmail) {
        this.adminid = adminid;
        this.adminpass = adminpass;
        this.adminname = adminname;
        this.adminsex = adminsex;
        this.departid = departid;
        this.adminiden = adminiden;
        this.adminphone = adminphone;
        this.adminmail = adminmail;
        this.department = department;
    }

    public Admin(String adminid, String adminpass, String adminname, String adminsex, Department department,
                 String adminiden, String adminphone, String adminmail, String adminphoto) {
        this.adminid = adminid;
        this.adminpass = adminpass;
        this.adminname = adminname;
        this.adminsex = adminsex;
        this.department = department;
        this.adminiden = adminiden;
        this.adminphone = adminphone;
        this.adminmail = adminmail;
        this.adminphoto = adminphoto;
    }

    public Admin(String adminid, String adminpass, String adminname, String adminsex, String departid, Department department,
                 String adminiden, String adminphone, String adminmail, String adminphoto, List<Students> studentsList) {
        this.adminid = adminid;
        this.adminpass = adminpass;
        this.adminname = adminname;
        this.adminsex = adminsex;
        this.departid = departid;
        this.adminiden = adminiden;
        this.adminphone = adminphone;
        this.adminmail = adminmail;
        this.adminphoto = adminphoto;
        this.studentsList = studentsList;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid='" + adminid + '\'' +
                ", adminpass='" + adminpass + '\'' +
                ", adminname='" + adminname + '\'' +
                ", adminsex='" + adminsex + '\'' +
                ", departid='" + departid + '\'' +
                ", adminiden='" + adminiden + '\'' +
                ", adminphone='" + adminphone + '\'' +
                ", adminmail='" + adminmail + '\'' +
                ", adminphoto='" + adminphoto + '\'' +
                ", studentsList=" + studentsList +
                ", department=" + department +
                '}';
    }
}