package com.flywithingryd.IngrydAirways.model;

import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity(name = "reservation")
public class Reservation {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Flight flightId;
    private List<Passenger> passenger;
    private Date departureTime;
    private Timestamp timestamp;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public Reservation(){};

    public Reservation(int id, Flight flightId, List<Passenger> passenger, Date departureTime, Timestamp timestamp, ReservationStatus status) {
        this.id = id;
        this.flightId = flightId;
        this.passenger = passenger;
        this.departureTime = departureTime;
        this.timestamp = timestamp;
        this.status = status;
    }
    public int getId(){
        return id;
    }

    public Flight getFlightId(){
        return flightId;
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


    //setters
    public void setFlightId(Flight flightId){
        this.flightId = flightId;
    }
    public void setPassenger(List<Passenger> passenger){
        this.passenger = passenger;
    }
    public void setStatus(ReservationStatus status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "id:"+getId() + "Flight" + getFlightId() + "Passenger:"
                + getPassenger() + "Date:" + getDepartureTime() + "TimeStamp:" + getTimestamp()
                + "ReservationSats:" + getStatus();
    }

    //Epic icon
    //FWI-38 ReservationStatus Management

}
