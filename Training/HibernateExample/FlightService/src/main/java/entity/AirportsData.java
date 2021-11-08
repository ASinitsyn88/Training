package entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "bookings", name = "airports_data")
@DynamicUpdate
@DynamicInsert
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entity.AirportsData")
public class AirportsData implements Serializable {

    private final static long serialVersionUID = 201105211233L;

    @Id
    @Column(name = "airport_code", unique = true, length = 3, nullable = false)
    private String airportCode;

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "city")
    private String city;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "timezone")
    private String timezone;

    public AirportsData() {

    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirportsData that = (AirportsData) o;

        if (airportCode != null ? !airportCode.equals(that.airportCode) : that.airportCode != null) return false;
        if (airportName != null ? !airportName.equals(that.airportName) : that.airportName != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (coordinates != null ? !coordinates.equals(that.coordinates) : that.coordinates != null) return false;
        return timezone != null ? timezone.equals(that.timezone) : that.timezone == null;
    }

    @Override
    public int hashCode() {
        int result = airportCode != null ? airportCode.hashCode() : 0;
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AirportsData{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}