package dal;

import org.hibernate.Session;
import org.hibernate.query.Query;
import reporting.Report02.EntityR02;
import reporting.Report03.EntityR03;
import view.ErrorDialog;

import java.util.ArrayList;
import java.util.Iterator;

import static valus.Strings.readStatusPerson;

/**
 * Created by hammer on 22.05.2018.
 */
public class ReportDAO_Hibernate implements ReportDAO {

    /**
     * запрос для выгрузки резервного списка
     *
     * @return
     */
    @Override
    public ArrayList<EntityR02> getListReport02() { //
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        int smenaNum = 99;
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        ArrayList<EntityR02> pp = new ArrayList<EntityR02>();

        Query query = session.createQuery("SELECT id AS id_, parentname, passportnum, parentipn, " +
                "CONCAT(street,' ',house,' ',flat) AS adres, district, " +
                "CONCAT(lastname,' ', firstname, ' ',middlename) AS name, " +
                "birth, mainphone, svnum, current_date AS lastYear, rsmena " +
                "FROM BookingPerson WHERE status=?1 AND smenanum=?2 ORDER BY name"); //HQL
        query.setParameter(1, readStatusPerson);
        query.setParameter(2, smenaNum);

//        Не очень красивая реализация
//        Iterator it = session.createQuery("SELECT id AS id_, parentname, passportnum, parentipn, " +
//                "street AS adres, district, " +
//                "CONCAT(lastname,' ', firstname, ' ',middlename) AS name, " +
//                "birth, mainphone, svnum, current_date AS lastYear, rsmena " +
//                "FROM BookingPerson WHERE status=1 AND smenanum=99").list().iterator();
//
        Iterator it = query.list().iterator();

        while (it.hasNext()) {
            Object[] row = (Object[]) it.next();
            pp.add(new EntityR02(String.valueOf(row[0]), String.valueOf(row[1]), String.valueOf(row[2]),
                    String.valueOf(row[3]), String.valueOf(row[4]), String.valueOf(row[5]),
                    String.valueOf(row[6]), String.valueOf(row[7]), String.valueOf(row[8]),
                    String.valueOf(row[9]), String.valueOf(row[10]), String.valueOf(row[11])
            ));
        }

//        for (int i = 0; i < pp.size(); i++) {
//            System.out.println(pp.get(i).toString());
//        }

        session.getTransaction().commit();
        session.close();
        return pp;
    }

    @Override
    public ArrayList<EntityR03> getListReport03(int smenaNum) { //
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        ArrayList<EntityR03> pp = new ArrayList<EntityR03>();

        Query query = session.createQuery("SELECT id AS id_, parentname, passportnum, parentipn, " +
                "CONCAT(street,' ',house,' ',flat) AS adres, district, " +
                "CONCAT(lastname,' ', firstname, ' ',middlename) AS name, " +
                "birth, mainphone, svnum, current_date AS lastYear, rsmena " +
                "FROM BookingPerson WHERE status=?1 AND smenanum=?2 ORDER BY name"); //HQL
        query.setParameter(1, readStatusPerson);
        query.setParameter(2, 99); // выгрузка резервной смены

        Iterator it = query.list().iterator();

        while (it.hasNext()) {
            Object[] row = (Object[]) it.next();

            String str = String.valueOf(row[11]);
            if (str.length() != 0) {
                String[] arrStr = str.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (arrStr[i].equals(String.valueOf(smenaNum))) {
                        pp.add(new EntityR03(String.valueOf(row[0]), String.valueOf(row[1]), String.valueOf(row[2]),
                                String.valueOf(row[3]), String.valueOf(row[4]), String.valueOf(row[5]),
                                String.valueOf(row[6]), String.valueOf(row[7]), String.valueOf(row[8]),
                                String.valueOf(row[9]), String.valueOf(row[10]), String.valueOf(row[11])
                        ));
                    }
                }
            }
        }

        session.getTransaction().commit();
        session.close();
        return pp;
    }
}


