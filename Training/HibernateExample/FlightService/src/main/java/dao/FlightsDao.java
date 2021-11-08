package dao;

import entity.Flights;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import static config.HibernateUtil.getSession;

public class FlightsDao {

    public List<Flights> byNo(String number) {
        List<Flights> result;
        try (final Session session = getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Flights> cq = cb.createQuery(Flights.class);
            Root<Flights> root = cq.from(Flights.class);

            // Загрузка списка дочерних объектов (билетов), которые есть у каждого объекта Flights
            // Загрузку дочерних объектов, а также глубину вложенности (дочерние объекты могут иметь свои списки) можно регулировать при помощи
            // более "продвинутого способа" под названием Entity Graph (см. аннотации в AircraftsData)
            // Способ с использованием Join оставлен для примера (как альтернатива Entity Graph)
            root.fetch("tickets", JoinType.INNER);

            cq.select(root).where(cb.equal(root.get("flightNo"), number)).distinct(true);
            result = session.createQuery(cq).getResultList();

            session.getTransaction().commit();
        }
        return result;
    }
}