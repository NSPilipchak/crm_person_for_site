package blogic;

/**
 * Created by hammer on 18.05.2018.
 */
public class EntityUtility {
    public EntityUtility() {
    }

    public static String YYYYMMDDtoDDMMYYY(String str) {
        if (str.length() < 2 || str.charAt(4) != '-')
            return str;

        String[] arr = str.split("-");
        String day = arr[2];
        String month = arr[1];
        String year = arr[0];
        return day + "." + month + "." + year;
    }

    public static String DDMMYYYYtoYYYYMMDD(String str) {
        if (str.charAt(2) != '.')
            return str;
        String[] arr = str.split("\\.");
        String day = arr[0];
        String month = arr[1];
        String year = arr[2];
        return year + "-" + month + "-" + day;
    }

}
