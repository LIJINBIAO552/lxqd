package zqu.lxqd.bean;

public class Department {
    private String departid;

    private String departname;

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid == null ? null : departid.trim();
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }

    public Department(String departid, String departname) {
        this.departid = departid;
        this.departname = departname;
    }

    public Department(){
    }

    @Override
    public String toString() {
        return "Department{" +
                "departid='" + departid + '\'' +
                ", departname='" + departname + '\'' +
                '}';
    }
}