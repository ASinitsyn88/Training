package dao;

import entity.AirportsData;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import static config.HibernateUtil.getSession;
import static config.HibernateUtil.getStatistics;

public class AirportsDataDao {

    public String create(AirportsData entity) {
        String code;
        try (final Session session = getSession()) {
            session.beginTransaction();

            // Скидываем объекты из оперативной памяти в БД. flush != commit
            // Рекомендуется использовать flush() для групп объектов чтобы не получить OutOfMemoryException
            session.flush();
            code = (String) session.save(entity);

            session.getTransaction().commit();
        }
        return code;
    }

    public void update(AirportsData entity) {
        try (final Session session = getSession()) {
            session.beginTransaction();

            session.update(entity);

            session.getTransaction().commit();
        }
    }

    public void delete(AirportsData entity) {
        try (final Session session = getSession()) {
            session.beginTransaction();

            session.delete(entity);

            session.getTransaction().commit();
        }
    }

    public List<AirportsData> findAll() {
        List<AirportsData> result;
        try (final Session session = getSession()) {
            session.beginTransaction();

            CriteriaQuery<AirportsData> criteria = session.getCriteriaBuilder().createQuery(AirportsData.class);
            criteria.from(AirportsData.class); // Указывается основная таблица
            result = session.createQuery(criteria).getResultList();

            session.getTransaction().commit();
        }
        printStat();
        return result;
    }

    public AirportsData byCode(String code) {
        AirportsData result;
        try (final Session session = getSession()) {
            session.beginTransaction();

            result = session.get(AirportsData.class, code);

            session.getTransaction().commit();
        }
        printStat();
        return result;
    }

    // Использование Criteria
    public List<AirportsData> byLetterInAirportCode(String letter) {
        List<AirportsData> result = null;
        try (final Session session = getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AirportsData> cq = cb.createQuery(AirportsData.class);
            Root<AirportsData> root = cq.from(AirportsData.class);

            cq.select(root).where(cb.like(root.get("airportCode"), "%" + letter + "%"));
            result = session.createQuery(cq).getResultList();

            session.getTransaction().commit();
        }
        return result;
    }

    // Использование HQL
    public List<AirportsData> byLetterInAirportCode2(String letter) {
        List<AirportsData> result = null;
        try (final Session session = getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM AirportsData WHERE airportCode LIKE :paramName");
            query.setParameter("paramName", "%" + letter + "%");
            result = query.getResultList();

            session.getTransaction().commit();
        }
        return result;
    }

    private static void printStat() {
        System.out.println("Выполнено запросов в БД: " + getStatistics().getQueryExecutionCount());
        System.out.println("Найдено в кэше: " + getStatistics().getSecondLevelCacheHitCount());
        System.out.println("Добавлено в кэш: " + getStatistics().getSecondLevelCachePutCount());
        getStatistics().clear();
    }
}