package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.dto.response.FlightSearchResponse;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.repository.FlightRepository;
import com.flywithingryd.IngrydAirways.specification.FlightSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    @Cacheable(cacheNames = "flights", key = "#request.origin + #request.destination + #request.departureDate + #pageable.pageNumber + #pageable.pageSize")
    public Page<FlightSearchResponse> searchFlights(FlightSearchRequest request, Pageable pageable) throws FlightNotFoundException {
        Page<Flight> flights = flightRepository.findAll(FlightSpecifications.searchFlights(request), pageable);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found for your search criteria.");
        }

        List<FlightSearchResponse> responseList = flights.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(responseList, pageable, flights.getTotalElements());
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight findFlightById(Long id) throws FlightNotFoundException {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
    }

    public Flight findFlightByFlightNumber(String flightNumber) throws FlightNotFoundException {
        Optional<Flight> flight = flightRepository.findAll().stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst();
        return flight.orElseThrow(() -> new FlightNotFoundException("Flight not found with flight number: " + flightNumber));
    }

    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    public Flight updateFlight(Long id, Flight flightDetails) throws FlightNotFoundException {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureDate(flightDetails.getDepartureDate());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setPrice(flightDetails.getPrice());
        flight.setAvailableSeats(flightDetails.getAvailableSeats());
        flight.setTravelClass(flightDetails.getTravelClass());
        flight.setOriginCode(flightDetails.getOriginCode());
        flight.setDestinationCode(flightDetails.getDestinationCode());

        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) throws FlightNotFoundException {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        flightRepository.delete(flight);
    }

    private FlightSearchResponse mapToResponse(Flight flight) {
        return new FlightSearchResponse(
                flight.getFlightNumber(),
                flight.getDepartureDate(),
                flight.getArrivalTime(),
                flight.getDepartureTime(),
                flight.getPrice(),
                flight.getAvailableSeats(),
                flight.getTravelClass().toString(),
                airportService.getAirportName(flight.getOriginCode()),
                airportService.getAirportName(flight.getDestinationCode())
        );
    }
}
