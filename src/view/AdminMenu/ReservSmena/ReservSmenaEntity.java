package view.AdminMenu.ReservSmena;

/**
 * Created by hammer on 17.05.2018.
 */
public class ReservSmenaEntity implements Comparable<ReservSmenaEntity> {

    private int id;
    private String data;
    private int max;

    public ReservSmenaEntity(int id, String data, int max) {
        this.id = id;
        this.data = data;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public int compareTo(ReservSmenaEntity o) {
        return ((Integer) id).compareTo(o.id);
    }
}