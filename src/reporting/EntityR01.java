package reporting;

/**
 * Created by hammer on 16.05.2018.
 */
public class EntityR01 implements Comparable<EntityR01>{
    private int number;
    private String parentFIO;
    private String parentPassp;
    private String adress;
    private String childrenFIO;
    private String childrenSv;
    private String blankNum;

    public EntityR01(int number, String parentFIO, String parentPassp, String adress, String childrenFIO, String childrenSv, String blankNum) {
        this.number = number;
        this.parentFIO = parentFIO;
        this.parentPassp = parentPassp;
        this.adress = adress;
        this.childrenFIO = childrenFIO;
        this.childrenSv = childrenSv;
        this.blankNum = blankNum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getParentFIO() {
        return parentFIO;
    }

    public void setParentFIO(String parentFIO) {
        this.parentFIO = parentFIO;
    }

    public String getParentPassp() {
        return parentPassp;
    }

    public void setParentPassp(String parentPassp) {
        this.parentPassp = parentPassp;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getChildrenFIO() {
        return childrenFIO;
    }

    public void setChildrenFIO(String childrenFIO) {
        this.childrenFIO = childrenFIO;
    }

    public String getChildrenSv() {
        return childrenSv;
    }

    public void setChildrenSv(String childrenSv) {
        this.childrenSv = childrenSv;
    }

    public String getBlankNum() {
        return blankNum;
    }

    public void setBlankNum(String blankNum) {
        this.blankNum = blankNum;
    }


    @Override
    public int compareTo(EntityR01 o) {
        return childrenFIO.compareToIgnoreCase(o.childrenFIO);
    }
}
