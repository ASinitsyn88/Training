package entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "bookings", name = "tickets")
@DynamicUpdate
@DynamicInsert
public class Tickets implements Serializable {

    private final static long serialVersionUID = 201105211236L;

    @Id
    @Column(name = "ticket_no", length = 13, unique = true, nullable = false)
    private String ticketNo;

    @Column(name = "book_ref", length = 6, nullable = false)
    private String bookRef;

    @Column(name = "passenger_id", length = 20, nullable = false)
    private String passengerId;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "contact_data", nullable = false)
    private String contactData;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getBookRef() {
        return bookRef;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tickets tickets = (Tickets) o;

        if (ticketNo != null ? !ticketNo.equals(tickets.ticketNo) : tickets.ticketNo != null) return false;
        if (bookRef != null ? !bookRef.equals(tickets.bookRef) : tickets.bookRef != null) return false;
        if (passengerId != null ? !passengerId.equals(tickets.passengerId) : tickets.passengerId != null) return false;
        if (passengerName != null ? !passengerName.equals(tickets.passengerName) : tickets.passengerName != null)
            return false;
        return contactData != null ? contactData.equals(tickets.contactData) : tickets.contactData == null;
    }

    @Override
    public int hashCode() {
        int result = ticketNo != null ? ticketNo.hashCode() : 0;
        result = 31 * result + (bookRef != null ? bookRef.hashCode() : 0);
        result = 31 * result + (passengerId != null ? passengerId.hashCode() : 0);
        result = 31 * result + (passengerName != null ? passengerName.hashCode() : 0);
        result = 31 * result + (contactData != null ? contactData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketNo='" + ticketNo + '\'' +
                ", bookRef='" + bookRef + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", contactData='" + contactData + '\'' +
                '}';
    }
}