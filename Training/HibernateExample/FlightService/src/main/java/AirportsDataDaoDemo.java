import dao.AirportsDataDao;
import entity.AirportsData;
import org.jboss.logging.Logger;

public class AirportsDataDaoDemo {

    private static final Logger LOG = Logger.getLogger(AirportsDataDaoDemo.class);

    public static void main(String[] args) {
        AirportsDataDao dao = new AirportsDataDao();
        // Получить информацию по всем аэропортам
        LOG.warn(dao.findAll());
        // Получить информацию по коду конкретного аэропорта
        LOG.warn(dao.byCode("LED"));
        // Найти аэропорты по вхождению буквы в код аэропорта
        LOG.warn(dao.byLetterInAirportCode("Y"));
        // Создать аэропорт
        String code = dao.create(buildEntity());
        // Изменить аэропорт
        AirportsData byCode = dao.byCode(code);
        byCode.setTimezone("Test");
        dao.update(byCode);
        // Удалить аэропорт
        dao.delete(byCode);
    }

    public static AirportsData buildEntity() {
        final AirportsData entity = new AirportsData();
        entity.setAirportCode("TST");
        entity.setAirportName("{\"en\": \"Test Airport\", \"ru\": \"Тест\"}");
        entity.setCity("{\"en\": \"Test\", \"ru\": \"Тест\"}");
        entity.setCoordinates("(130.77099609375,63.093299865722656)");
        entity.setTimezone("Europe/Moscow");
        return entity;
    }
}