package dal;

import blogic.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.ErrorDialog;

import java.io.File;

import static valus.Strings.DBLogin;
import static valus.Strings.DBPass;
import static valus.Strings.DBUrl;


public class HibernateUtil {
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
////			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//            File file = new File("./hibernate.cfg.xml");
//            sessionFactory = new Configuration().configure(file).addAnnotatedClass(BookingPerson.class).buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            new ErrorDialog("Отсутствует соединение с БД");
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }

    private static final SessionFactory sessionFactory
            = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    //    /**
//     * Создание фабрики
//     * @return {@link SessionFactory}
//     * @throws HibernateException
//     */
    private static SessionFactory configureSessionFactory()
            throws HibernateException {

        // Настройки hibernate
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://" + DBUrl)
                .setProperty("hibernate.connection.username", DBLogin)
                .setProperty("hibernate.connection.password", DBPass)
//                .setProperty("hibernate.connection.pool_size", "1")    //not for production use!

//                <!-- c3p0 config http://www.hibernate.org/214.html -->
                .setProperty("connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider")
                .setProperty("hibernate.c3p0.acquire_increment", "1")
                .setProperty("hibernate.c3p0.idle_test_period", "60")
                .setProperty("hibernate.c3p0.min_size", "1")
                .setProperty("hibernate.c3p0.max_size", "2")
                .setProperty("hibernate.c3p0.max_statements", "50")
                .setProperty("hibernate.c3p0.timeout", "0")
                .setProperty("hibernate.c3p0.acquireRetryAttempts", "1")
                .setProperty("hibernate.c3p0.acquireRetryDelay", "250")

                .setProperty("hibernate.connection.characterEncoding", "UTF-8")
                .setProperty("hibernate.connection.autocommit", "false")                             // такой нет в ХМЛ
                .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")// такой нет в ХМЛ
                .setProperty("hibernate.cache.use_second_level_cache", "false")                      // такой нет в ХМЛ
                .setProperty("hibernate.cache.use_query_cache", "false")                             // такой нет в ХМЛ
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "false")
                .setProperty("hibernate.use_sql_comments", "false")
//                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.current_session_context_class", "thread")
//                .setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
                .addPackage("ru.miralab.db")                                                         // такой нет в ХМЛ
                .addAnnotatedClass(App.class)
                .addAnnotatedClass(Smena.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(BookingPerson2017.class)
                .addAnnotatedClass(BookingPerson.class);

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Получить фабрику сессий
     *
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}