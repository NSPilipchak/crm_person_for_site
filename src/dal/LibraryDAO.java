package dal;


import blogic.App;

import java.util.ArrayList;

public interface LibraryDAO {
    ArrayList<App> getAppList();

    String getValueByKey(String key);

    App getAppByKey(String key);

    void updateApp(App a);

    int countSmena(int param, int smenaID, int status, int rtype);
}