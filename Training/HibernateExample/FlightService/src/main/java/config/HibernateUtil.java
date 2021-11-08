package config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

/**
 * Класс со статическими методами для инициализации сессии
 * Считывает настройки из hibernate.cfg.xml
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Statistics stat;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            stat = sessionFactory.getStatistics();
            stat.clear();
            stat.setStatisticsEnabled(true);
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            return sessionFactory.openSession();
        }
    }

    public static Statistics getStatistics() {
        return stat;
    }
}