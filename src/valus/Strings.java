package valus;

import properties.AppSettings;

import java.awt.*;

/**
 * Created by hammer on 26.05.2017.
 */
public class Strings {
    public static String DBUrl = AppSettings.get("DBurl").toString()
            + ":3306/"
            + AppSettings.get("DBname").toString()
            + "?useSSL=false";

    public static String DBLogin = (String) AppSettings.get("DBuser");
    public static String DBPass = (String) AppSettings.get("DBpass");


    public static String WORK_USER_NAME = null;
    public static int WORK_USER_PERMISSION;

    //насройки диалоговых окон
    public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static int DG_WIDH = 525;
    public static int DG_HEIGH = 600;

    public static int fullTable = 0; // 1 - полная таблица, 0 краткая таблица
    public static int readSmenaNumber = 1;
    public static int readStatusPerson = 1;

    public static boolean DB_AUTO_UPDATE = Integer.parseInt((String) AppSettings.get("AutoUpdateDB")) == 1;
    public static int DB_UPDATE_TIME = Integer.parseInt((String) AppSettings.get("DateBaseUpdateTime"));
    public static boolean EDIT_LAST_YEAR = Integer.parseInt((String) AppSettings.get("EditableLastYear")) == 1;

    public static boolean SAVE_GENERATED_REPORT = Integer.parseInt((String) AppSettings.get("SaveGeneratedReport")) == 1;
    public static String REPORT_PATH = (String) AppSettings.get("ReportPath");
    public static String GENERATED_REPORT_PATH = (String) AppSettings.get("GeneratedReportPath");

    public static int COUNT_LEVEL_STATUS_BAR = Integer.parseInt((String) AppSettings.get("CountLevelStatusBar"));
    public static int TABLE_ROW_HEIGHT = Integer.parseInt((String) AppSettings.get("TableRowHeight"));
    public static int TABLE_FONT_SIZE = Integer.parseInt((String) AppSettings.get("TableFontSize"));

    public static final String[] listBus = AppSettings.get("Bus").toString().split(",");

    public static final String[] USER_ROLES = {
            "Пользователь",
            "Администратор"
    };
    public static final String[] USER_STATUS = {
            "Доступ закрыт",
            "Доступ разрешен"
    };

    public static final String[] itemSexChildren = {""
            , "Мальчик"
            , "Девочка"};

    public static final String[] itemFirstTime = {"Первый раз"
            , "Уже был"};

    public static final String[] itemSmenaDate2017 = {"2017-05-29"
            , "2017-06-05"
            , "2017-06-12"
            , "2017-06-19"
            , "2017-06-26"
            , "2017-07-03"
            , "2017-07-10"
            , "2017-07-17"
            , "2017-07-24"
            , "2017-07-31"
            , "2017-08-07"
            , "2017-08-14"
            , "2017-08-21"
            , "2018-01-01"};

    public static final String[] itemSmenaDateRus = {"04.06.2018"
            , "11.06.2018"
            , "18.06.2018"
            , "25.06.2018"
            , "02.07.2018"
            , "09.07.2018"
            , "16.07.2018"
            , "23.07.2018"
            , "30.07.2018"
            , "06.08.2018"
            , "13.08.2018"
            , "20.08.2018"
            , "27.08.2018"
            , "01.01.2019"};

    public static final String[] itemSmenaDateRus2017 = {"29.05.2017"
            , "05.06.2017"
            , "12.06.2017"
            , "19.06.2017"
            , "26.06.2017"
            , "03.07.2017"
            , "10.07.2017"
            , "17.07.2017"
            , "24.07.2017"
            , "31.07.2017"
            , "07.08.2017"
            , "14.08.2017"
            , "21.08.2017"
            , "01.01.2018"};

    public static final int[] itemSmenaNumber = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 99};

    public static final String[] itemDistrict = {
            "---",
            "Амур-Нижньодніпровський",
            "Індустріальный",
            "Новокодацький (Ленінський)",
            "Самарський",
            "Соборний (Жовтневий)",
            "Центральний (Кіровський)",
            "Чечеловський (Красногвардійський)",
            "Шевченківський (Бабушкінський)"
    };

    public static final String[] itemStatusCard = {
            "Новая",
            "Выдано направление",
            "Запрошены документы",
            "Отказ"
    };

    // 2-й этап регистрации:
    public static final String[] itemStatusSecondLevel = {
            "Подтверждение НЕ отправленно",
            "В очереди на отправку",
            "Подтверждение отправленно"
    };

    public static boolean permissionAdmin(int i) {
        if (i == 1) {
            return true;
        }
        return false;
    }
}
