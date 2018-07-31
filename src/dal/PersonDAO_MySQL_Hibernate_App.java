package dal;

import org.hibernate.Session;
import org.hibernate.query.Query;
import secure.App;

/**
 * Created by hammer on 30.05.2017.
 */
public class PersonDAO_MySQL_Hibernate_App {
    public App read() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM BookingPerson"); //HQL
        App app = (App) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return app;
    }
}
