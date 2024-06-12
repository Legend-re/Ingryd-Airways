package com.flywithingryd.IngrydAirways.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.model.enums.SeatClass;
import com.flywithingryd.IngrydAirways.repository.FlightRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FlightService.class})
@ExtendWith(SpringExtension.class)
class FlightServiceDiffblueTest {
    @MockBean
    private AirportService airportService;

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    /**
     * Method under test:
     * {@link FlightService#searchFlights(FlightSearchRequest, Pageable)}
     */
    @Test
    void testSearchFlights() throws FlightNotFoundException {
        when(flightRepository.findAll(Mockito.<Specification<Flight>>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertThrows(FlightNotFoundException.class, () -> flightService
                .searchFlights(new FlightSearchRequest("Origin", "Destination", SeatClass.ECONOMY, 10, "2020-03-01"), null));
        verify(flightRepository).findAll(Mockito.<Specification<Flight>>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link FlightService#searchFlights(FlightSearchRequest, Pageable)}
     */
    @Test
    void testSearchFlights2() throws FlightNotFoundException {
        Aircraft aircraft = new Aircraft();
        aircraft.setBusinessClassSeats(1);
        aircraft.setEconomyClassSeats(1);
        aircraft.setFlight(new Flight());
        aircraft.setModel("No flights found for your search criteria.");
        aircraft.setRegistrationNumber("42");
        aircraft.setSeatCapacity(1);
        aircraft.setStatus(AircraftStatus.ACTIVE);
        aircraft.setYearManufactured(1);

        Flight flight = new Flight();
        flight.setAircraft(aircraft);
        flight.setArrivalTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight.setAvailableSeats(1);
        flight.setDepartureDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight.setDepartureTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight.setDestinationCode("No flights found for your search criteria.");
        flight.setFlightNumber("42");
        flight.setId(1L);
        flight.setOriginCode("No flights found for your search criteria.");
        flight.setPrice(10.0d);
        flight.setStatus(FlightStatus.CREATED);
        flight.setTravelClass(SeatClass.ECONOMY);

        Aircraft aircraft2 = new Aircraft();
        aircraft2.setBusinessClassSeats(1);
        aircraft2.setEconomyClassSeats(1);
        aircraft2.setFlight(flight);
        aircraft2.setModel("No flights found for your search criteria.");
        aircraft2.setRegistrationNumber("42");
        aircraft2.setSeatCapacity(1);
        aircraft2.setStatus(AircraftStatus.ACTIVE);
        aircraft2.setYearManufactured(1);

        Flight flight2 = new Flight();
        flight2.setAircraft(aircraft2);
        flight2.setArrivalTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight2.setAvailableSeats(1);
        flight2.setDepartureDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight2.setDepartureTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        flight2.setDestinationCode("No flights found for your search criteria.");
        flight2.setFlightNumber("42");
        flight2.setId(1L);
        flight2.setOriginCode("No flights found for your search criteria.");
        flight2.setPrice(10.0d);
        flight2.setStatus(FlightStatus.CREATED);
        flight2.setTravelClass(SeatClass.ECONOMY);

        ArrayList<Flight> content = new ArrayList<>();
        content.add(flight2);
        PageImpl<Flight> pageImpl = new PageImpl<>(content);
        when(flightRepository.findAll(Mockito.<Specification<Flight>>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);
        when(airportService.getAirportName(Mockito.<String>any()))
                .thenThrow(new FlightNotFoundException("An error occurred"));
        assertThrows(FlightNotFoundException.class, () -> flightService
                .searchFlights(new FlightSearchRequest("Origin", "Destination", SeatClass.ECONOMY, 10, "2020-03-01"), null));
        verify(airportService).getAirportName(Mockito.<String>any());
        verify(flightRepository).findAll(Mockito.<Specification<Flight>>any(), Mockito.<Pageable>any());
    }
}
