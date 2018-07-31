package reporting.Report02;

/**
 * Created by hammer on 16.05.2018.
 */
public class EntityR02 implements Comparable<EntityR02> {
    private String id_;
    private String parentname;
    private String passportnum;
    private String parentipn;
    private String adres;
    private String district;
    private String name;
    private String birth;
    private String mainphone;
    private String svnum;
    private String lastYear;
    private String rsmena;

    public EntityR02(String id_, String parentname, String passportnum,
                     String parentipn, String adres, String district,
                     String name, String birth, String mainphone,
                     String svnum, String lastYear, String rsmena) {
        this.id_ = id_;
        this.parentname = parentname;
        this.passportnum = passportnum;
        this.parentipn = parentipn;
        this.adres = adres;
        this.district = district;
        this.name = name;
        this.birth = birth;
        this.mainphone = mainphone;
        this.svnum = svnum;
        this.lastYear = lastYear;
        this.rsmena = rsmena;
    }

    public String getId_() {
        return id_;
    }

    public void setId(String id_) {
        this.id_ = id_;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getPassportnum() {
        return passportnum;
    }

    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    public String getParentipn() {
        return parentipn;
    }

    public void setParentipn(String parentipn) {
        this.parentipn = parentipn;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMainphone() {
        return mainphone;
    }

    public void setMainphone(String mainphone) {
        this.mainphone = mainphone;
    }

    public String getSvnum() {
        return svnum;
    }

    public void setSvnum(String svnum) {
        this.svnum = svnum;
    }

    public String getLastYear() {
        return lastYear;
    }

    public void setLastYear(String lastYear) {
        this.lastYear = lastYear;
    }

    public String getRsmena() {
        return rsmena;
    }

    public void setRsmena(String rsmena) {
        this.rsmena = rsmena;
    }

    @Override
    public int compareTo(EntityR02 o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public String toString() {
        return "EntityR02{" +
                "id=" + id_ +
                ", parentname='" + parentname + '\'' +
                ", passportnum='" + passportnum + '\'' +
                ", parentipn='" + parentipn + '\'' +
                ", adres='" + adres + '\'' +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", mainphone='" + mainphone + '\'' +
                ", svnum='" + svnum + '\'' +
                ", lastYear='" + lastYear + '\'' +
                ", rsmena='" + rsmena + '\'' +
                '}';
    }
}
