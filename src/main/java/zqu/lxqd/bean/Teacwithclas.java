package zqu.lxqd.bean;

public class Teacwithclas {
    private Integer twcid;

    private String adminId;

    private String smajor;

    private String ssclass;

    private String sgrade;

    public Teacwithclas(){

    }

    public Integer getTwcid() {
        return twcid;
    }

    public void setTwcid(Integer twcid) {
        this.twcid = twcid;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getSmajor() {
        return smajor;
    }

    public void setSmajor(String smajor) {
        this.smajor = smajor == null ? null : smajor.trim();
    }

    public String getSsclass() {
        return ssclass;
    }

    public void setSsclass(String ssclass) {
        this.ssclass = ssclass == null ? null : ssclass.trim();
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade == null ? null : sgrade.trim();
    }


}