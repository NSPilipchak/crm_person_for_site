package blogic;

/**
 * Created by hammer on 24.05.2017.
 */
public class BaseStatus{

    private String[] smenaCount;

    public BaseStatus() {
    }

    @Override
    public String toString() {
        String ret = "BaseStatus{";

        for (int i = 0; i < smenaCount.length; i++) {
            ret += "smenaCount_" + i + "=" + smenaCount[i] + " ";
        }
        return ret + " }";
    }
}
