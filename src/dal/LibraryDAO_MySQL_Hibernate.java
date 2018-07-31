package dal;

import blogic.App;
import org.hibernate.Session;
import org.hibernate.query.Query;
import view.ErrorDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hammer on 05.09.2017.
 */
public class LibraryDAO_MySQL_Hibernate implements LibraryDAO {

    @Override
    public ArrayList<App> getAppList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        ArrayList<App> apps = (ArrayList<App>) session.createQuery("from App order by id").list();
        session.getTransaction().commit();
        session.close();
        return apps;
    }

    @Override
    public String getValueByKey(String key) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from App where mkey like :search");
        query.setParameter("search", key);
        ArrayList<App> result = (ArrayList<App>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result.get(0).getValue();
    }

    @Override
    public App getAppByKey(String key) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from App where mkey like :search");
        query.setParameter("search", key);
        ArrayList<App> result = (ArrayList<App>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result.get(0);
    }

    @Override
    public void updateApp(App a) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(a);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public int countSmena(int param, int smenaID, int status, int rtype) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        Query query = null;

        switch (param) {
            case 0: // c указанием типа ссылки
                query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND status=?2 AND rtype=?3"); //HQL
                query.setParameter(1, smenaID);
                query.setParameter(2, status);
                query.setParameter(3, rtype);
                break;
            case 1: // без указанием типа ссылки
                query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND status=?2"); //HQL
                query.setParameter(1, smenaID);
                query.setParameter(2, status);
                break;
            case 2: // все кто не по основной ссылке
                query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND status=?2 AND rtype!=?3"); //HQL
                query.setParameter(1, smenaID);
                query.setParameter(2, status);
                query.setParameter(3, rtype);
                break;
        }
        int count = Integer.parseInt(String.valueOf(query.getResultList().get(0)));
        session.getTransaction().commit();
        session.close();
        return count;
    }
}
