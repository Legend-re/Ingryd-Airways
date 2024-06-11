package com.flywithingryd.IngrydAirways.model;

import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String flightNumber;
    @NotBlank
    @NotNull
    private String departure;
    @NotBlank
    @NotNull
    private String arrival;
    @NotBlank
    @NotNull
    private LocalDateTime departureTime;
    @NotBlank
    @NotNull
    private FlightStatus status;
    @NotBlank
    @NotNull
    @OneToOne
    @JoinColumn(name= "aircraft_id")
    private Aircraft aircraft;
    @NotBlank
    @NotNull
    private int availableSeats;
    @NotBlank
    @NotNull
    private double price;


    public Flight(String flightNumber, String departure, String arrival, LocalDateTime departureTime,
                  FlightStatus status, Aircraft aircraft, int availableSeats, double price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.status = status;
        this.aircraft = aircraft;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureTime=" + departureTime +
                ", status=" + status +
                ", aircraft=" + aircraft +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                '}';
    }
}
