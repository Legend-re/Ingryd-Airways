package com.flywithingryd.IngrydAirways.dto.request;

import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.model.enums.SeatClass;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FlightRequest {
    private String flightNumber;

    private String destinationCode;

    private String originCode;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private FlightStatus status;

    private Aircraft aircraft;

    private SeatClass travelClass;

    private int availableSeats;

    private double price;

    private LocalDate departureDate;
}
