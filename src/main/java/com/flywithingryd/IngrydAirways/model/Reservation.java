package com.flywithingryd.IngrydAirways.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.Objects;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String reservationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Flight flight;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Passenger> passengers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @Transient
    private Itinerary itineraryId;

    public Reservation(){};

    public Reservation(int id, String reservationNumber, Flight flight, Set<Passenger> passengers, Date departureTime, Timestamp timestamp, ReservationStatus status) {
        this.id = id;
        this.flight = flight;
        this.reservationNumber = reservationNumber;
        this.passengers = passengers;
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
    public Set<Passenger> getPassengers(){
        return passengers;
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
    public void setPassengers(Set<Passenger> passengers){
        this.passengers = passengers;
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
                ", passenger=" + passengers +
                ", departureTime=" + departureTime +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
