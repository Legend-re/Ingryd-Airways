package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "reservation")
public class Reservation {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne()
    @JoinColumn(name ="id")
    private User user;
    @OneToOne
    @JoinColumn(name = "flight_number")
    private Flight flight;
    private String passenger;
    private Date dateTime;
    private Timestamp timestamp;

    public Reservation(){};

    public Reservation(int id, User user, Flight flight, String passenger, Date dateTime, Timestamp timestamp) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.passenger = passenger;
        this.dateTime = dateTime;
        this.timestamp = timestamp;
    }
    public int getId(){
        return id;
    }

    public User getUser(){
        return user;
    }
    public Flight getFlight(){
        return flight;
    }
    public String getPassenger(){
        return passenger;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
    //setters
    public void setUser(User user){
        this.user = user;
    }
    public void setFlight(Flight flight){
        this.flight = flight;
    }
    public void setPassenger(String passenger){
        this.passenger = passenger;
    }

    @Override
    public String toString(){
        return "id:"+getId() + "User:" + getUser() + "Flight" + getFlight() + "Passenger:"
                + getPassenger() + "Date:" + getDateTime() + "TimeStamp:" + getTimestamp();
    }

}
