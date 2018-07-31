package dal;

import reporting.Report02.EntityR02;
import reporting.Report03.EntityR03;

import java.util.ArrayList;

/**
 * Created by hammer on 22.05.2018.
 */
public interface ReportDAO {
    ArrayList<EntityR02> getListReport02();
    ArrayList<EntityR03> getListReport03(int smenaNum);
}
