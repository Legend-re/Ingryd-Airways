package com.flywithingryd.IngrydAirways.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.model.enums.SeatClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String flightNumber;

    @NotBlank
    @Column(name = "destination_airport_code", nullable = false)
    private String destinationCode;

    @NotBlank
    @Column(name = "origin_airport_code", nullable = false)
    private String originCode;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull
    private LocalDateTime arrivalTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Aircraft aircraft;

    @Enumerated(EnumType.STRING)
    private SeatClass travelClass;

    private int availableSeats;

    @DecimalMin("50.0")
    private double price;

    @OneToMany
    @JoinColumn(name = "")
    @JsonIgnore
    private List<Itinerary> itineraryList;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Reservation> reservation;

    public Flight(String flightNumber, String destinationCode, String originCode,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  FlightStatus status, Aircraft aircraft, SeatClass travelClass,
                  int availableSeats, double price, LocalDate departureDate) {
        this.flightNumber = flightNumber;
        this.destinationCode = destinationCode;
        this.originCode = originCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.aircraft = aircraft;
        this.travelClass = travelClass;
        this.availableSeats = availableSeats;
        this.price = price;
        this.departureDate = departureDate;
    }

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public SeatClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(SeatClass travelClass) {
        this.travelClass = travelClass;
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Set<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", destinationCode='" + destinationCode + '\'' +
                ", originCode='" + originCode + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", status=" + status +
                ", aircraft=" + aircraft +
                ", travelClass=" + travelClass +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                ", departureDate=" + departureDate +
                '}';
    }
}
