package blogic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by hammer on 18.07.2017.
 */
@Entity
@Table(name = "smena")
public class Smena implements Comparable<Smena> {

    @Id
    private int id;
    private int places;
    private String name;
    private String date_start;
    private String date_end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_start() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(date_start);
    }

    public void setDate_start(String date_start) {
        this.date_start = EntityUtility.DDMMYYYYtoYYYYMMDD(date_start);
    }

    public String getDate_end() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(date_end);
    }

    public void setDate_end(String date_end) {
        this.date_end = EntityUtility.DDMMYYYYtoYYYYMMDD(date_end);
    }

    @Override
    public int compareTo(Smena o) {
        return 0;
    }
}
