import dao.AirportsDataDao;
import entity.AirportsData;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class AirportsDataDaoTest {

    @Test
    public void createTest() {
        AirportsDataDao dao = new AirportsDataDao();
        // Создать аэропорт
        String code = dao.create(buildEntity());
        // Получить информацию по коду конкретного аэропорта
        AirportsData found = dao.byCode("T11");

        assertNotNull(found);
    }

    public static AirportsData buildEntity() {
        final AirportsData entity = new AirportsData();
        entity.setAirportCode("T11");
        entity.setAirportName("{\"en\": \"Test Airport\", \"ru\": \"Тест\"}");
        entity.setCity("{\"en\": \"Test\", \"ru\": \"Тест\"}");
        entity.setCoordinates("(130.77099609375,63.093299865722656)");
        entity.setTimezone("Europe/Moscow");
        return entity;
    }
}
