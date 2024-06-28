package com.flywithingryd.IngrydAirways.service;

import org.springframework.stereotype.Service;

import java.util.Map;

// this is mock data please Legendre convert to local Nigerian Airports

@Service
public class LocalAirportService {
    private final Map<String, String> airportData = Map.of(
            "JFK", "John F. Kennedy International Airport",
            "LAX", "Los Angeles International Airport",
            "ORD", "O'Hare International Airport",
            "LOS", "Muritala Muhammed Airport"
    );

    public String getAirportName(String code) {
        return airportData.getOrDefault(code, "Unknown Airport");
    }
}