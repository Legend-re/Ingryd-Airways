package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    //create flight
    public Flight createFlight(Flight flight){
        flight.setStatus(FlightStatus.CREATED);
        return flightRepository.save(flight);
    }

    //find flight
    public Flight findFlightById(long id){
        Flight newFlight = flightRepository.findById(id).get();
        return newFlight;
    }

    //find flight by FLIGHT_NO.
    public Flight findFlightByFlightNumber(String flightNumber){
        return flightRepository.findFlightByFlightNumber(flightNumber);
    }

    //find all flights
    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    //update flight
    public Flight updateFlight(Flight flight, long id){
        Flight findId = flightRepository.findById(id).get();
        findId.setAircraft(flight.getAircraft());
        findId.setArrival(flight.getArrival());
        findId.setDeparture(flight.getDeparture());
        findId.setPrice(flight.getPrice());
        findId.setStatus(flight.getStatus());

        flight.setStatus(FlightStatus.SCHEDULED);

        return flightRepository.save(findId);
    }

}
