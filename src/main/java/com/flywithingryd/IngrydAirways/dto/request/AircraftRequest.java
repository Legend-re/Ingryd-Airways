package com.flywithingryd.IngrydAirways.dto.request;

import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import lombok.Data;

@Data
public class AircraftRequest {

    private String model;

    private int yearManufactured;

    private String registrationNumber;

    private AircraftStatus status;

    private int seatCapacity;

    private int businessClassSeats;

    private int economyClassSeats;
}
