package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.FlightRequest;
import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.dto.response.FlightSearchResponse;
import com.flywithingryd.IngrydAirways.exception.AircraftNotFoundException;
import com.flywithingryd.IngrydAirways.exception.DuplicateEntityException;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.repository.AircraftRepository;
import com.flywithingryd.IngrydAirways.repository.FlightRepository;
import com.flywithingryd.IngrydAirways.specification.FlightSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    private static final String PREFIX = "IA";

    private final FlightRepository flightRepository;

    private final AirportService airportService;

    private final AircraftRepository aircraftRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportService airportService, AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
        this.aircraftRepository = aircraftRepository;
    }

    //@Cacheable(cacheNames = "flights", key = "#request.origin + #request.destination + #request.departureDate + #pageable.pageNumber + #pageable.pageSize")
    public List<FlightSearchResponse> searchFlights(String origin) throws FlightNotFoundException {
        List<Flight> flights = flightRepository.findByOriginCode(origin);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found for your search criteria.");
        }

        return flights.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

/*    public List<Flight> searchFlightByOrigin(Double origin) throws FlightNotFoundException{
        try {
//            Pageable pageable = PageRequest.of(pageIndex, pageSize);
//            Page<Flight> flights = flightRepository.findByOriginCode(origin, pageable);
//            System.out.println(flights.getContent());
                // List<Flight> flights = flightRepository.findByOriginCode(origin);
            List<Flight> flights = findAllFlights();
            System.out.println(flights);
            return flights.stream().filter(flight -> flight.getPrice()==origin).toList();
        } catch (Exception exception){
            logger.warn("Error fetching Flights ", exception);
            throw new FlightNotFoundException(String.format("Unable to fetch Flights with origin %s", origin));
        } finally {
            logger.info("done processing list Flight request");
        }
    }*/

    String generateFlightNumber() {
        StringBuilder flightNumber = new StringBuilder(PREFIX);

        int randomNumber = new Random().nextInt(9999) + 1;
        flightNumber.append(String.format("%04d", randomNumber));

        return flightNumber.toString();
    }

    public Flight createFlight(Integer hours, String aircraftRegNumber, FlightRequest request) {
        logger.info("processing create flight");

        Aircraft existingActiveAircraft = aircraftRepository.findByRegistrationNumberAndStatus(aircraftRegNumber, AircraftStatus.ACTIVE)
                .orElseThrow( () -> new AircraftNotFoundException("Aircraft Does not Exist or is under maintenance"));

        Flight newFlight = new Flight();
        String flightNumber;

        do {
            flightNumber = generateFlightNumber();
        }
        while (flightRepository.findByFlightNumber(flightNumber).isPresent());

        newFlight.setFlightNumber(flightNumber);
        newFlight.setDestinationCode(request.getDestinationCode());
        newFlight.setOriginCode(request.getOriginCode());
        newFlight.setDepartureDate(request.getDepartureDate());
        newFlight.setStatus(FlightStatus.CREATED);
        newFlight.setPrice(request.getPrice());
        newFlight.setDepartureDate(request.getDepartureDate());
        newFlight.setDepartureTime(request.getDepartureTime());
        newFlight.setArrivalTime(request.getDepartureTime().plusHours(hours));


        if (existingActiveAircraft.getFlight() == null){
            newFlight.setAircraft(existingActiveAircraft);
            newFlight.setAvailableSeats(existingActiveAircraft.getSeatCapacity());
            newFlight.setStatus(FlightStatus.SCHEDULED);
        } else {
            throw new DuplicateEntityException("Another Flight is tied to this aircraft");
        }

        return flightRepository.save(newFlight);
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
                airportService.getAirportName(flight.getOriginCode()),
                airportService.getAirportName(flight.getDestinationCode())
        );
    }
}
