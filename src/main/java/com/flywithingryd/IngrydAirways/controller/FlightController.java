package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.dto.response.FlightSearchResponse;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flight Management", description = "API for managing flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "Search Flights", description = "Search for flights based on criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flights found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlightSearchResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Flights not found",
                    content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<Page<FlightSearchResponse>> searchFlights(
            @Parameter(description = "Search request", required = true) FlightSearchRequest request,
            @Parameter(description = "Pageable") Pageable pageable) throws FlightNotFoundException {
        return ResponseEntity.ok(flightService.searchFlights(request, pageable));
    }

    @Operation(summary = "Create Flight", description = "Create a new flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Flight> createFlight(
            @Parameter(description = "Flight details", required = true) @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @Operation(summary = "Find Flight by ID", description = "Find a flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(
            @Parameter(description = "Flight ID", required = true) @PathVariable Long id) throws FlightNotFoundException {
        return ResponseEntity.ok(flightService.findFlightById(id));
    }

    @Operation(summary = "Find Flight by Flight Number", description = "Find a flight by its flight number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<Flight> findFlightByFlightNumber(
            @Parameter(description = "Flight number", required = true) @PathVariable String flightNumber) throws FlightNotFoundException {
        return ResponseEntity.ok(flightService.findFlightByFlightNumber(flightNumber));
    }

    @Operation(summary = "Find All Flights", description = "Retrieve all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flights retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))})
    })
    @GetMapping
    public ResponseEntity<List<Flight>> findAllFlights() {
        return ResponseEntity.ok(flightService.findAllFlights());
    }
}