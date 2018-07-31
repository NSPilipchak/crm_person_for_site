package reporting.Report05_listBirth;

/**
 * Created by hammer on 16.05.2018.
 */
public class EntityR05 implements Comparable<EntityR05> {
    private int id;
    private String code;
    private String childrenFIO;
    private String childrenBirth;

    public EntityR05(int id, String code, String childrenFIO, String childrenBirth) {
        this.id = id;
        this.code = code;
        this.childrenFIO = childrenFIO;
        this.childrenBirth = childrenBirth;
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

    @Override
    public int compareTo(EntityR05 o) {
        return childrenFIO.compareToIgnoreCase(o.childrenFIO);
    }
}
