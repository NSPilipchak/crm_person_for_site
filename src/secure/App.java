package secure;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hammer on 30.05.2017.
 */
@Entity
@Table(name = "app")
public class App {

    private int id;
    private String mkey;
    private String value;

    public App() {
    }

    public App(int id, String mkey, String value) {
        init(id, mkey,value);
    }

    public void init(int id, String mkey, String value) {
        this.id = id;
        this.mkey = mkey;
        this.value = value;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", mkey='" + mkey + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
