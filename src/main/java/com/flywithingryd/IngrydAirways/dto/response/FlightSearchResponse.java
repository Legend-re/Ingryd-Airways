package com.flywithingryd.IngrydAirways.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchResponse {
    private String flightNumber;
    private LocalDate departureDate;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private double price;
    private int availableSeats;
    private String travelClass;
    private String originAirportName;
    private String destinationAirportName;
}

