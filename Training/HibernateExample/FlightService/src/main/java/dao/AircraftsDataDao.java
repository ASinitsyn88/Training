package dao;

import entity.AircraftsData;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import static config.HibernateUtil.getSession;

public class AircraftsDataDao {

    public AircraftsData byCode(String code) {
        AircraftsData result;
        try (final Session session = getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AircraftsData> cq = cb.createQuery(AircraftsData.class);
            Root<AircraftsData> root = cq.from(AircraftsData.class);
            // Загрузка списка дочерних объектов (рейсов), которые есть у каждого объекта AircraftsData
            // Загрузку дочерних объектов, а также глубину вложенности (дочерние объекты могут иметь свои списки) можно регулировать при помощи
            // более "продвинутого способа" под названием Entity Graph (см. аннотации в AircraftsData)
            // Способ с использованием Join закомментирован и оставлен для примера
            // root.fetch("flights", JoinType.INNER);


            cq.select(root).where(cb.equal(root.get("aircraftCode"), code)).distinct(true);
            Query<AircraftsData> query = session.createQuery(cq);
            // Способы загрузки дочерних объектов:
            // javax.persistence.fetchgraph - указанные поля - EAGER, неуказанные поля - LAZY
            // javax.persistence.loadgraph - указанные поля - EAGER, неуказанные поля - в зависимости от настроек или по-умолчанию
            query.setHint("javax.persistence.fetchgraph", session.getEntityGraph("AircraftsData.flights"));
            result = query.getSingleResult();

            session.getTransaction().commit();
        }
        return result;
    }
}