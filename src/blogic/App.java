package blogic;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app")
public class App implements Comparable<App>, Serializable {

    @Id
    @Column(name = "id", unique = true, length = 10)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false, length = 250)
    private String mkey;
    @Column(length = 250)
    private String value;

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

    @Override
    public int compareTo(App o) {
        return mkey.compareToIgnoreCase(o.mkey);
    }
}
