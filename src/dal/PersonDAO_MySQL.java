package dal;

import blogic.BookingPerson;
import blogic.EntityUtility;
import blogic.Smena;
import org.hibernate.Session;
import org.hibernate.query.Query;
import view.ErrorDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;

import static valus.Strings.*;

public class PersonDAO_MySQL implements PersonDAO {

    @Override
    public void create(BookingPerson p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ArrayList<BookingPerson> read() {
        long startNano = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
//        Query query = session.createQuery("FROM BookingPerson bp ORDER BY bp.id"); //HQL
        ArrayList<BookingPerson> pp = null;
        if ((readSmenaNumber + readStatusPerson) != 0) {
            if (readStatusPerson == 0) { // вычитка одной смены без ограничений
                Query query = session.createQuery("FROM BookingPerson WHERE smenanum=?1"); //HQL
                query.setParameter(1, readSmenaNumber);
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 1) { // вычитка только Активных персон
                Query query = session.createQuery("FROM BookingPerson WHERE status=?1"); //HQL
                query.setParameter(1, readStatusPerson);
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 1) { // вычитка активных по смене
                Query query = session.createQuery("FROM BookingPerson WHERE smenanum=?1 AND status=?2"); //HQL
                query.setParameter(1, readSmenaNumber);
                query.setParameter(2, readStatusPerson);
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 3) { // вычитка заблокированніх
                Query query = session.createQuery("FROM BookingPerson WHERE status=?1 AND checkinn!=?2"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, "-");
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 3) { // вычитка заблокированніх
                Query query = session.createQuery("FROM BookingPerson WHERE status=?1 AND smenanum=?2 AND checkinn!=?3"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, readSmenaNumber);
                query.setParameter(3, "-");
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber == 0 && readStatusPerson == 2) { // вычитка удаленных
                Query query = session.createQuery("FROM BookingPerson WHERE status=?1 AND checkinn=?2"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, "-");
                pp = (ArrayList<BookingPerson>) query.getResultList();
            } else if (readSmenaNumber != 0 && readStatusPerson == 2) { // вычитка удаленных
                Query query = session.createQuery("FROM BookingPerson WHERE status=?1 AND smenanum=?2 AND checkinn=?3"); //HQL
                query.setParameter(1, 0);
                query.setParameter(2, readSmenaNumber);
                query.setParameter(3, "-");
                pp = (ArrayList<BookingPerson>) query.getResultList();
            }
            session.getTransaction().commit();
            session.close();
        } else { // Вычитка полного списка персон

            session.getTransaction().commit();
            session.close();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                pp = new ArrayList<>();
//                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false", "root" ,"1225");
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBUrl, DBLogin, DBPass);
                con.setAutoCommit(false);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM booking");
                while (rs.next()) {
                    pp.add(new BookingPerson(
                            rs.getInt("id"),
                            rs.getString("code"),
                            rs.getString("firstname"),
                            rs.getString("middlename"),
                            rs.getString("lastname"),
                            rs.getInt("sex"),
                            rs.getString("birth"),
                            rs.getString("school"),
                            rs.getString("parentname"),
                            rs.getString("parentipn"),
                            rs.getString("passportnum"),
                            rs.getString("passportinfo"),
                            rs.getString("passportdate"),
                            rs.getString("mainphone"),
                            rs.getString("addphone"),
                            rs.getString("city"),
                            rs.getString("district"),
                            rs.getString("street"),
                            rs.getString("house"),
                            rs.getString("corp"),
                            rs.getString("flat"),
                            rs.getString("svnum"),
                            rs.getString("svdate"),
                            rs.getString("smenadate"),
                            rs.getInt("smenanum"),
                            rs.getInt("ftime"),
                            rs.getInt("gtype"),
                            rs.getInt("gnum"),
                            rs.getString("email"),
                            rs.getString("checkinn"),
                            rs.getInt("sendmail"),
                            rs.getInt("status"),
                            rs.getInt("rtype"),
                            rs.getInt("visit"),
                            rs.getString("comment"),
                            rs.getInt("statusCard"),
                            rs.getString("rsmena"),
                            rs.getInt("age"),
                            rs.getString("boardingPass")
                    ));
//                    rs.getInt("id"), rs.getString(2), rs.getString("lname"), rs.getInt(4), rs.getString("phone")) );
                }
                con.commit();
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
//        session.getTransaction().commit();
//        session.close();
        long endNano = System.currentTimeMillis();
        long timeConsumedMillis = endNano - startNano;
        System.out.println("PersonDAO_MySQL: read() " + timeConsumedMillis + "ms");
        return pp;
    }

    @Override
    public void update(BookingPerson p) {
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
    public void delete(BookingPerson p) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public String[][] checkBase(int smenaSize) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }

        // 1 строка статуса - Итого занято мест (только активные персоны)
        Query query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND status='1'"); //HQL
        String[][] count = new String[smenaSize + 1][4];
        for (int i = 0; i < count.length; i++) {
            if (i < count.length - 1)
                count[i][0] = String.valueOf(query.setParameter(1, i + 1).getResultList());
            else
                count[i][0] = String.valueOf(query.setParameter(1, 99).getResultList());
        }
        // 3 строка статуса - кол-во персон прошедших проверку, отправленно письмо с подтверждением
        query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND sendmail='2' AND status='1'"); //HQL
        for (int i = 0; i < count.length; i++) {
            if (i < count.length - 1)
                count[i][1] = String.valueOf(query.setParameter(1, i + 1).getResultList());
            else
                count[i][1] = String.valueOf(query.setParameter(1, 99).getResultList());
        }
        // 4 строка статуса - кол-во получивших посадочный талон
        query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND boardingPass='1' AND status='1'"); //HQL
        for (int i = 0; i < count.length; i++) {
            if (i < count.length - 1)
                count[i][2] = String.valueOf(query.setParameter(1, i + 1).getResultList());
            else
                count[i][2] = String.valueOf(query.setParameter(1, 99).getResultList());
        }
        // 5 строка статуса - кол-во фактически посетивших лагерь
        query = session.createQuery("SELECT COUNT(*) FROM BookingPerson WHERE smenanum=?1 AND visit='1' AND status='1'"); //HQL
        for (int i = 0; i < count.length; i++) {
            if (i < count.length - 1)
                count[i][3] = String.valueOf(query.setParameter(1, i + 1).getResultList());
            else
                count[i][3] = String.valueOf(query.setParameter(1, 99).getResultList());
        }
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public int[] checkReserv(int smenaSize) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        int[] ret = new int[smenaSize + 1];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = 0;
        }
        Iterator itReserv = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT rsmena FROM BookingPerson WHERE smenanum='99' AND status='1'");
            itReserv = query.list().iterator();

            session.getTransaction().commit();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        session.close();

        if (itReserv == null)
            return ret;
        while (itReserv.hasNext()) {
            String str = (String) itReserv.next();
            if (str.length() >= 1 && !str.equals("0") && !str.equals(0)) {
                String[] arr = str.split(",");
                for (int i = 0; i < arr.length; i++) {
                    ret[Integer.parseInt(arr[i]) - 1]++;
                }
            } else {
                ret[ret.length - 1]++;
            }

        }
        return ret;
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
    public String[] readSmenaString() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String[] ret = null;
        try {
            session.beginTransaction();
            ArrayList<Smena> pp = null;
            Query query = session.createQuery("FROM Smena"); //HQL
            pp = (ArrayList<Smena>) query.getResultList();
            ret = new String[pp.size() + 1];
            int i;
            for (i = 0; i < pp.size(); i++) {
                String str = pp.get(i).getId() + " смена " + EntityUtility.YYYYMMDDtoDDMMYYY(pp.get(i).getDate_start());
                ret[i] = str;
            }
            ret[i] = "Р Е З Е Р В";
            session.getTransaction().commit();
            session.close();
        } catch (Throwable ex) {
            new ErrorDialog("Соединение с БД потеряно");
        }
        return ret;
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
    public ArrayList<BookingPerson> searchPerson(String search, int parametr, boolean strongSearch) {
        /**
         * Принимаемый параметр:
         * 1 - поиск по Фамилии
         * 2 - поиск по ЧекИНН
         * 3 - поиск по Cв о рождении
         * 4 - поиск по идентификатору/коду
         * 5 - поиск по дате рождения - добавленно 20.11.17
         */

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query;
        String str = "";

        switch (parametr) {
            case 1:
                break;
            case 2:
                str = "from BookingPerson where checkinn like :search order by id";
                break;
            case 3:
                str = "from BookingPerson p where svnum like :search order by id";
                break;
        }

        query = session.createQuery(str);
        if (strongSearch)
            query.setParameter("search", search);
        else
            query.setParameter("search", "%" + search + "%");

        ArrayList<BookingPerson> pp = (ArrayList<BookingPerson>) query.list();
        session.getTransaction().commit();
        session.close();
        return pp;
    }
}
