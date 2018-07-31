package properties;

import java.io.File;

/**
 * Created by hammer on 11.07.2017.
 */
public class Main {
    private final static String propDir = "./";
    private final static String WIDTH_KEY = "MainFrame.width";
    private final static String HEIGHT_KEY = "MainFrame.height";

    public static void main(String[] args) {
        try {
            File file = new File(propDir, "settings.xml");
            AppSettings.clear();
            AppSettings.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(
                "AppSettings.getInt(WIDTH_KEY, 100) " +
                        AppSettings.getInt(WIDTH_KEY, 100));
        System.out.println(
                        "AppSettings.getInt(HEIGHT_KEY, 100) " +
                        AppSettings.getInt(HEIGHT_KEY, 100));


    }
}
