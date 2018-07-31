package reporting.Report04;

/**
 * Created by hammer on 16.05.2018.
 */
public class EntityR04 implements Comparable<EntityR04> {
    private int id;
    private String code;
    private String parentFIO;
    private String parentPasspNum;
    private String parentPasspInfo;
    private String parentPasspData;
    private String childrenFIO;
    private String childrenBirth;
    private String smenaStart;
    private String smenaEnd;
    private String parentInn;

    public EntityR04(int id, String code, String parentFIO, String parentPasspNum, String parentPasspInfo, String parentPasspData, String childrenFIO, String childrenBirth, String smenaStart, String smenaEnd, String parentInn) {
        this.id = id;
        this.code = code;
        this.parentFIO = parentFIO;
        this.parentPasspNum = parentPasspNum;
        this.parentPasspInfo = parentPasspInfo;
        this.parentPasspData = parentPasspData;
        this.childrenFIO = childrenFIO;
        this.childrenBirth = childrenBirth;
        this.smenaStart = smenaStart;
        this.smenaEnd = smenaEnd;
        this.parentInn = parentInn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentFIO() {
        return parentFIO;
    }

    public void setParentFIO(String parentFIO) {
        this.parentFIO = parentFIO;
    }

    public String getParentPasspNum() {
        return parentPasspNum;
    }

    public void setParentPasspNum(String parentPasspNum) {
        this.parentPasspNum = parentPasspNum;
    }

    public String getParentPasspInfo() {
        return parentPasspInfo;
    }

    public void setParentPasspInfo(String parentPasspInfo) {
        this.parentPasspInfo = parentPasspInfo;
    }

    public String getParentPasspData() {
        return parentPasspData;
    }

    public void setParentPasspData(String parentPasspData) {
        this.parentPasspData = parentPasspData;
    }

    public String getChildrenFIO() {
        return childrenFIO;
    }

    public void setChildrenFIO(String childrenFIO) {
        this.childrenFIO = childrenFIO;
    }

    public String getChildrenBirth() {
        return childrenBirth;
    }

    public void setChildrenBirth(String childrenBirth) {
        this.childrenBirth = childrenBirth;
    }

    public String getSmenaStart() {
        return smenaStart;
    }

    public void setSmenaStart(String smenaStart) {
        this.smenaStart = smenaStart;
    }

    public String getSmenaEnd() {
        return smenaEnd;
    }

    public void setSmenaEnd(String smenaEnd) {
        this.smenaEnd = smenaEnd;
    }

    public String getParentInn() {
        return parentInn;
    }

    public void setParentInn(String parentInn) {
        this.parentInn = parentInn;
    }

    @Override
    public int compareTo(EntityR04 o) {
        return childrenFIO.compareToIgnoreCase(o.childrenFIO);
    }
}
