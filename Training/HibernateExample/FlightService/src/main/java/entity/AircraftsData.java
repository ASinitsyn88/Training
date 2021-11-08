package entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(schema = "bookings", name = "aircrafts_data")
@DynamicUpdate
@DynamicInsert

@NamedEntityGraphs({
        @NamedEntityGraph(name = "AircraftsData.flights", attributeNodes = {
                @NamedAttributeNode(value = "flights")
        })
})
public class AircraftsData implements Serializable {

    private final static long serialVersionUID = 201105211234L;

    @Id
    @Column(name = "aircraft_code", unique = true, length = 3, nullable = false)
    private String aircraftCode;

    @Column(name = "model")
    private String model;

    @Column(name = "range")
    private Integer range;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Flights.class, mappedBy = "aircraftCode") // Один самолёт может принимать участие во множестве рейсов
    private Set<Flights> flights = new TreeSet<>();

    public AircraftsData() {

    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Set<Flights> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flights> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AircraftsData that = (AircraftsData) o;

        if (aircraftCode != null ? !aircraftCode.equals(that.aircraftCode) : that.aircraftCode != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return range != null ? range.equals(that.range) : that.range == null;
    }

    @Override
    public int hashCode() {
        int result = aircraftCode != null ? aircraftCode.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AircraftsData{" +
                "aircraftCode='" + aircraftCode + '\'' +
                ", model='" + model + '\'' +
                ", range=" + range +
                '}';
    }
}