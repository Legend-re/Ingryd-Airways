package com.flywithingryd.IngrydAirways.model;

import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String reservationNumber;

    @ManyToOne
    private Flight flight;

    @OneToMany
    private List<Passenger> passenger;

    private Date departureTime;
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @OneToOne
    @Transient
    private Itinerary itineraryId;

    public Reservation(){};

    public Reservation(int id, String reservationNumber, Flight flight, List<Passenger> passenger, Date departureTime, Timestamp timestamp, ReservationStatus status) {
        this.id = id;
        this.flight = flight;
        this.reservationNumber = reservationNumber;
        this.passenger = passenger;
        this.departureTime = departureTime;
        this.timestamp = timestamp;
        this.status = status;
    }
    public int getId(){
        return id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }
    public Flight getFlight() {
        return flight;
    }
    public List<Passenger> getPassenger(){
        return passenger;
    }
    public Date getDepartureTime() {
        return departureTime;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
    public ReservationStatus getStatus(){
        return status;
    }
    public Itinerary getItineraryId() {
        return itineraryId;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public void setPassenger(List<Passenger> passenger){
        this.passenger = passenger;
    }
    public ReservationStatus setStatus(ReservationStatus status){
        return  this.status = status;
    }
    public void setItineraryId(Itinerary itineraryId) {
        this.itineraryId = itineraryId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationNumber='" + reservationNumber + '\'' +
                ", flight=" + flight +
                ", passenger=" + passenger +
                ", departureTime=" + departureTime +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

}
