package zqu.lxqd.bean;

public class Lxqd {
    private String lxqdId;

    private String title;

    private String acaddesc;

    private String studaffadividesc;

    private String logimanadesc;

    private String librarydesc;

    private String hotwater1name;

    private String hotwater1desc;

    private String hotwater2name;

    private String hotwater2desc;

    private String educandcompdesc;
    
    private String classteacdesc;

    private String remarkdesc;

    public String getLxqdId() {
        return lxqdId;
    }

    public void setLxqdId(String lxqdId) {
        this.lxqdId = lxqdId == null ? null : lxqdId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAcaddesc() {
        return acaddesc;
    }

    public void setAcaddesc(String acaddesc) {
        this.acaddesc = acaddesc == null ? null : acaddesc.trim();
    }

    public String getStudaffadividesc() {
        return studaffadividesc;
    }

    public void setStudaffadividesc(String studaffadividesc) {
        this.studaffadividesc = studaffadividesc == null ? null : studaffadividesc.trim();
    }

    public String getLogimanadesc() {
        return logimanadesc;
    }

    public void setLogimanadesc(String logimanadesc) {
        this.logimanadesc = logimanadesc == null ? null : logimanadesc.trim();
    }

    public String getLibrarydesc() {
        return librarydesc;
    }

    public void setLibrarydesc(String librarydesc) {
        this.librarydesc = librarydesc == null ? null : librarydesc.trim();
    }

    public String getHotwater1name() {
        return hotwater1name;
    }

    public void setHotwater1name(String hotwater1name) {
        this.hotwater1name = hotwater1name == null ? null : hotwater1name.trim();
    }

    public String getHotwater1desc() {
        return hotwater1desc;
    }

    public void setHotwater1desc(String hotwater1desc) {
        this.hotwater1desc = hotwater1desc == null ? null : hotwater1desc.trim();
    }

    public String getHotwater2name() {
        return hotwater2name;
    }

    public void setHotwater2name(String hotwater2name) {
        this.hotwater2name = hotwater2name == null ? null : hotwater2name.trim();
    }

    public String getHotwater2desc() {
        return hotwater2desc;
    }

    public void setHotwater2desc(String hotwater2desc) {
        this.hotwater2desc = hotwater2desc == null ? null : hotwater2desc.trim();
    }

    public String getEducandcompdesc() {
        return educandcompdesc;
    }

    public void setEducandcompdesc(String educandcompdesc) {
        this.educandcompdesc = educandcompdesc == null ? null : educandcompdesc.trim();
    }

    public String getClassteacdesc() {
        return classteacdesc;
    }

    public void setClassteacdesc(String classteacdesc) {
        this.classteacdesc = classteacdesc;
    }

    public String getRemarkdesc() {
        return remarkdesc;
    }

    public void setRemarkdesc(String remarkdesc) {
        this.remarkdesc = remarkdesc == null ? null : remarkdesc.trim();
    }

    public Lxqd(){

    }

    public Lxqd(String lxqdId, String title, String acaddesc, String studaffadividesc, String logimanadesc, String librarydesc, String hotwater1name, String hotwater1desc, String hotwater2name, String hotwater2desc, String educandcompdesc, String classteacdesc, String remarkdesc) {
        this.lxqdId = lxqdId;
        this.title = title;
        this.acaddesc = acaddesc;
        this.studaffadividesc = studaffadividesc;
        this.logimanadesc = logimanadesc;
        this.librarydesc = librarydesc;
        this.hotwater1name = hotwater1name;
        this.hotwater1desc = hotwater1desc;
        this.hotwater2name = hotwater2name;
        this.hotwater2desc = hotwater2desc;
        this.educandcompdesc = educandcompdesc;
        this.classteacdesc = classteacdesc;
        this.remarkdesc = remarkdesc;
    }

    @Override
    public String toString() {
        return "Lxqd{" +
                "lxqdId='" + lxqdId + '\'' +
                ", title='" + title + '\'' +
                ", acaddesc='" + acaddesc + '\'' +
                ", studaffadividesc='" + studaffadividesc + '\'' +
                ", logimanadesc='" + logimanadesc + '\'' +
                ", librarydesc='" + librarydesc + '\'' +
                ", hotwater1name='" + hotwater1name + '\'' +
                ", hotwater1desc='" + hotwater1desc + '\'' +
                ", hotwater2name='" + hotwater2name + '\'' +
                ", hotwater2desc='" + hotwater2desc + '\'' +
                ", educandcompdesc='" + educandcompdesc + '\'' +
                ", classteacdesc='" + classteacdesc + '\'' +
                ", remarkdesc='" + remarkdesc + '\'' +
                '}';
    }
}