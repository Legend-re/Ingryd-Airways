package com.flywithingryd.IngrydAirways.model;

import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
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
    @NotBlank
    @NotNull
    private int seatCapacity;

    @NotNull
    @NotBlank
    private int businessClassSeats;
    @NotNull
    @NotBlank
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
}