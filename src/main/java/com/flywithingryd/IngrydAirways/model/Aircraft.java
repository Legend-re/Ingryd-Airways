package com.flywithingryd.IngrydAirways.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "aircraft", uniqueConstraints = {@UniqueConstraint
        (columnNames = "registration_number")})
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String model;

    private int yearManufactured;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private AircraftStatus status;

    @NotNull
    @Min(150)
    private int seatCapacity;

    @NotNull
    @Min(1)
    @Max(50)
    private int businessClassSeats;

    @NotNull
    @Min(1)
    private int economyClassSeats;

    public Aircraft() {
    }

    public Aircraft(String model, int yearManufactured, String registrationNumber, AircraftStatus status, int seatCapacity, int businessClassSeats, int economyClassSeats) {
        this.model = model;
        this.yearManufactured = yearManufactured;
        this.registrationNumber = registrationNumber;
        this.status = status;
        this.seatCapacity = seatCapacity;
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economyClassSeats;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public AircraftStatus getStatus() {
        return status;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public int getBusinessClassSeats() {
        return businessClassSeats;
    }

    public void setBusinessClassSeats(int businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    public int getEconomyClassSeats() {
        return economyClassSeats;
    }

    public void setEconomyClassSeats(int economyClassSeats) {
        this.economyClassSeats = economyClassSeats;
    }

    @OneToOne(mappedBy = "aircraft")
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", yearManufactured=" + yearManufactured +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", status=" + status +
                ", seatCapacity=" + seatCapacity +
                ", businessClassSeats=" + businessClassSeats +
                ", economyClassSeats=" + economyClassSeats +
                '}';
    }
}