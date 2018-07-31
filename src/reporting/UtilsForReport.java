package reporting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by hammer on 16.05.2018.
 */
public class UtilsForReport {

    public UtilsForReport() {
    }

    public static String getDate() {
        LocalDate date = LocalDate.now();
        return reversDateToEuro(date.toString());
    }

    public static String getTime() {
        LocalTime time = LocalTime.now();
        String ret = time.toString();
        String[] arr = ret.split("\\.");
        return arr[0].toString();
    }

    public static String getTimeNoDot() {
        String ret = getTime();
        String[] arr = ret.split(":");
        return arr[0] + "-" + arr[1] + "-" + arr[2];
    }

    public static void main(String[] args) {
        new UtilsForReport();
//        System.out.println(getDate());
//        System.out.println(getTime());
        System.out.println(getLastDaySmena(getDate()));
    }

    public static String reversDateToEuro(String str) {
        if (str.charAt(4) != '-')
            return str;
        String[] arr = str.split("-");
        return arr[2] + "." + arr[1] + "." + arr[0];
    }

    public static String getLastDaySmena(String smenaStart) {
        String[] arr = smenaStart.split("\\.");
        int day = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int year = Integer.parseInt(arr[2]);
        LocalDate date = LocalDate.of(year, month, day);
        date = date.plusDays(5);
        arr = date.toString().split("-");
        return arr[2] + "." + arr[1] + "." + arr[0];
    }

    public static String createFileName(String strId) {

        String dataTime = UtilsForReport.getDate() + "_" +
                UtilsForReport.getTimeNoDot();
        String id = "" + strId;
        String[] arr = id.split("-");
        id = arr[0];

        return "_" +
                id + "_" +
                dataTime;
    }

    public static ArrayList<EntityR01> sortArrayList(ArrayList<EntityR01> dm) {
        dm.sort(EntityR01::compareTo);
        for (int i = 0; i < dm.size(); i++) {
            dm.get(i).setNumber(i + 1);
        }
        return dm;
    }


    //для проверки дня рождения в период смены
    public static boolean birthday(String startDate, String endDate, String bDate) {
        LocalDate dateStart = getData(startDate);
        LocalDate dateEnd = getData(endDate);
        LocalDate dateEqualse = getData(bDate);
        return eqalseDateFromPeriod(dateStart, dateEnd, dateEqualse, false);
    }

    private static LocalDate getData(String string) {
        String[] arr = string.split("\\.");
        int day = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int year = Integer.parseInt(arr[2]);
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    private static boolean eqalseDateFromPeriod(LocalDate dateStart, LocalDate dateEnd, LocalDate dateEqualse, boolean checkYear) {
        System.out.println("dateStart " + dateStart + "; dateEnd " + dateEnd + "; dateEqualse " + dateEqualse);
        dateStart = dateStart.minusDays(1);
        dateEnd = dateEnd.plusDays(1);
        System.out.println("dateStart " + dateStart + "; dateEnd " + dateEnd + "; dateEqualse " + dateEqualse);


        while (!eqalseDate(dateStart, dateEnd)) {
            if (checkYear) {
                if (dateStart.getDayOfMonth() == dateEqualse.getDayOfMonth() &&
                        dateStart.getMonth() == dateEqualse.getMonth() &&
                        dateStart.getYear() == dateEqualse.getYear())
                    return true;
            } else {
                if (dateStart.getDayOfMonth() == dateEqualse.getDayOfMonth() &&
                        dateStart.getMonth() == dateEqualse.getMonth())
                    return true;
            }
            dateStart = dateStart.plusDays(1);
        }
        return false;
    }

    private static boolean eqalseDate(LocalDate date1, LocalDate date2) {
        if (date1.getDayOfMonth() == date2.getDayOfMonth() &&
                date1.getMonth() == date2.getMonth())
            return true;
        return false;
    }
}
