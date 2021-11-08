import dao.AircraftsDataDao;
import entity.AircraftsData;
import org.jboss.logging.Logger;

public class AircraftsDataDaoDemo {

    private static final Logger LOG = Logger.getLogger(AircraftsDataDaoDemo.class);

    public static void main(String[] args) {
        AircraftsDataDao dao = new AircraftsDataDao();
        AircraftsData aircraftsData = dao.byCode("773");
        LOG.warn(aircraftsData);
    }
}