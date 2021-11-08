import dao.FlightsDao;
import entity.Flights;
import org.jboss.logging.Logger;
import java.util.List;

public class FlightsDaoDemo {

    private static final Logger LOG = Logger.getLogger(FlightsDaoDemo.class);

    public static void main(String[] args) {
        FlightsDao dao = new FlightsDao();
        List<Flights> flights = dao.byNo("PG0216");
        LOG.warn(flights);
    }
}