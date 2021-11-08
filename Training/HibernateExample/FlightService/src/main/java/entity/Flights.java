package entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(schema = "bookings", name = "flights")
@DynamicUpdate
@DynamicInsert
public class Flights implements Serializable {

    private final static long serialVersionUID = 201105211235L;

    @Id
    @Column(name = "flight_id", unique = true, nullable = false)
    private Integer flightId;

    @Column(name = "flight_no", length = 6)
    private String flightNo;

    @Column(name = "scheduled_departure")
    private LocalDateTime scheduledDeparture;

    @Column(name = "scheduled_arrival")
    private LocalDateTime scheduledArrival;

    @ManyToOne // у многих рейсов может быть один аэропорт отправления
    @JoinColumn(name = "departure_airport")
    private AirportsData departureAirport;

    @ManyToOne // у многих рейсов может быть один аэропорт прибытия
    @JoinColumn(name = "arrival_airport")
    private AirportsData arrivalAirport;

    @Column(name = "status", length = 20)
    private String status;

    @ManyToOne // у многих рейсов может быть один и тот же самолёт
    @JoinColumn(name = "aircraft_code")
    private AircraftsData aircraftCode;

    @Column(name = "actual_departure")
    private LocalDateTime actualDeparture;

    @Column(name = "actual_arrival")
    private LocalDateTime actualArrival;

    @OneToMany(cascade = CascadeType.ALL) // На один рейс может быть куплено множество билетов
    @JoinTable(
            name = "ticket_flights",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_no")
    )
    private Set<Tickets> tickets = new TreeSet<>();

    public Flights() {

    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public AirportsData getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportsData departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportsData getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportsData arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AircraftsData getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(AircraftsData aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public LocalDateTime getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(LocalDateTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public LocalDateTime getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(LocalDateTime actualArrival) {
        this.actualArrival = actualArrival;
    }

    public Set<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Tickets> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flights flights = (Flights) o;

        if (flightId != null ? !flightId.equals(flights.flightId) : flights.flightId != null) return false;
        if (flightNo != null ? !flightNo.equals(flights.flightNo) : flights.flightNo != null) return false;
        if (scheduledDeparture != null ? !scheduledDeparture.equals(flights.scheduledDeparture) : flights.scheduledDeparture != null)
            return false;
        if (scheduledArrival != null ? !scheduledArrival.equals(flights.scheduledArrival) : flights.scheduledArrival != null)
            return false;
        if (departureAirport != null ? !departureAirport.equals(flights.departureAirport) : flights.departureAirport != null)
            return false;
        if (arrivalAirport != null ? !arrivalAirport.equals(flights.arrivalAirport) : flights.arrivalAirport != null)
            return false;
        if (status != null ? !status.equals(flights.status) : flights.status != null) return false;
        if (aircraftCode != null ? !aircraftCode.equals(flights.aircraftCode) : flights.aircraftCode != null)
            return false;
        if (actualDeparture != null ? !actualDeparture.equals(flights.actualDeparture) : flights.actualDeparture != null)
            return false;
        if (actualArrival != null ? !actualArrival.equals(flights.actualArrival) : flights.actualArrival != null)
            return false;
        return tickets != null ? tickets.equals(flights.tickets) : flights.tickets == null;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (flightNo != null ? flightNo.hashCode() : 0);
        result = 31 * result + (scheduledDeparture != null ? scheduledDeparture.hashCode() : 0);
        result = 31 * result + (scheduledArrival != null ? scheduledArrival.hashCode() : 0);
        result = 31 * result + (departureAirport != null ? departureAirport.hashCode() : 0);
        result = 31 * result + (arrivalAirport != null ? arrivalAirport.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (aircraftCode != null ? aircraftCode.hashCode() : 0);
        result = 31 * result + (actualDeparture != null ? actualDeparture.hashCode() : 0);
        result = 31 * result + (actualArrival != null ? actualArrival.hashCode() : 0);
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flightId=" + flightId +
                ", flightNo='" + flightNo + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", scheduledArrival=" + scheduledArrival +
                ", departureAirport=" + departureAirport +
                ", arrivalAirport=" + arrivalAirport +
                ", status='" + status + '\'' +
                ", aircraftCode=" + aircraftCode +
                ", actualDeparture=" + actualDeparture +
                ", actualArrival=" + actualArrival +
                ", tickets=" + tickets +
                '}';
    }
}