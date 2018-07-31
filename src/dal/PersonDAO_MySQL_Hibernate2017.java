package dal;

import blogic.BookingPerson;
import blogic.BookingPerson2017;
import blogic.Smena;
import org.hibernate.Session;
import org.hibernate.query.Query;
import view.ErrorDialog;

import java.util.ArrayList;

import static valus.Strings.readSmenaNumber;
import static valus.Strings.readStatusPerson;

public class PersonDAO_MySQL_Hibernate2017 implements PersonDAO2017 {

    @Override
    public void create(BookingPerson2017 p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ArrayList<BookingPerson2017> read() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
//        Query query = session.createQuery("FROM BookingPerson bp ORDER BY bp.id"); //HQL
        ArrayList<BookingPerson2017> pp = null;
        if ((readSmenaNumber + readStatusPerson) != 0) {
            if (readStatusPerson == 0) { // вычитка одной смены без ограничений
                Query query = session.createQuery("FROM BookingPerson2017 WHERE smenanum=?1"); //HQL
                query.setParameter(1, readSmenaNumber);
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 1) { // вычитка только Активных персон
                Query query = session.createQuery("FROM BookingPerson2017 WHERE status=?1"); //HQL
                query.setParameter(1, readStatusPerson);
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 1) { // вычитка активных по смене
                Query query = session.createQuery("FROM BookingPerson2017 WHERE smenanum=?1 AND status=?2"); //HQL
                query.setParameter(1, readSmenaNumber);
                query.setParameter(2, readStatusPerson);
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 3) { // вычитка заблокированніх
                Query query = session.createQuery("FROM BookingPerson2017 WHERE status=?1 AND checkinn!=?2"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, "-");
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 3) { // вычитка заблокированніх
                Query query = session.createQuery("FROM BookingPerson2017 WHERE status=?1 AND smenanum=?2 AND checkinn!=?3"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, readSmenaNumber);
                query.setParameter(3, "-");
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 2) { // вычитка удаленных
                Query query = session.createQuery("FROM BookingPerson2017 WHERE status=?1 AND checkinn=?2"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, "-");
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 2) { // вычитка удаленных
                Query query = session.createQuery("FROM BookingPerson2017 WHERE status=?1 AND smenanum=?2 AND checkinn=?3"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, readSmenaNumber);
                query.setParameter(3, "-");
                pp = (ArrayList<BookingPerson2017>) query.getResultList();
            }
        } else { // Вычитка полного списка персон
            Query query = session.createQuery("FROM BookingPerson2017"); //HQL
            pp = (ArrayList<BookingPerson2017>) query.getResultList();
        }
        session.getTransaction().commit();
        session.close();
        return pp;
    }

    @Override
    public void update(BookingPerson2017 p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        session.update(p);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(BookingPerson2017 p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public String[] checkBase() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }


        Query query = session.createQuery("SELECT COUNT(*) FROM BookingPerson2017 WHERE smenanum=?1 AND status='1'"); //HQL
        String[] count = new String[13];
        for (int i = 0; i < count.length; i++) {
            count[i] = String.valueOf(query.setParameter(1, i + 1).getResultList());
        }
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public ArrayList<Smena> readSmena() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
//        Query query = session.createQuery("FROM BookingPerson bp ORDER BY bp.id"); //HQL
        ArrayList<Smena> pp = null;
        // Вычитка полного списка персон
        Query query = session.createQuery("FROM Smena"); //HQL
        pp = (ArrayList<Smena>) query.getResultList();

        session.getTransaction().commit();
        session.close();
        return pp;
    }

    @Override
    public void updateSmena(Smena s) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        session.update(s);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public ArrayList<BookingPerson2017> getPersonByChekinn(String inn) {
        ArrayList<BookingPerson2017> personList = new ArrayList<>();
        if (inn.equals("-"))
            return personList;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }

        Query query = session.createQuery("FROM BookingPerson2017 WHERE checkinn=?2");
        query.setParameter(2, inn);

        personList = (ArrayList<BookingPerson2017>) query.list();
        session.getTransaction().commit();
        session.close();
        return personList;
    }
}
