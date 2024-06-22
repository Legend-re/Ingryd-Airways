package com.flywithingryd.IngrydAirways.controller.rest;

import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.dto.response.FlightSearchResponse;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.service.AirportService;
import com.flywithingryd.IngrydAirways.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.MANAGE_FLIGHT_CONTROLLER_ENDPOINT;

@RestController
@RequestMapping(MANAGE_FLIGHT_CONTROLLER_ENDPOINT)
@Tag(name = "Flight Management", description = "API for managing and searching flights")
public class FlightRestController {

    private final FlightService flightService;
    private final AirportService airportService;
    private static final Logger logger = LoggerFactory.getLogger(FlightRestController.class);

    @Autowired
    public FlightRestController(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    @PostMapping("/search")
    @Operation(
            summary = "Search for flights",
            description = "Search for flights based on origin, destination, and other criteria."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flight search successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlightSearchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid origin or destination code",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flights not found",
                    content = @Content)
    })
    public ResponseEntity<?> searchFlights(
            @RequestBody @Valid FlightSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        logger.info("Searching flights from {} to {}", request.getOrigin(), request.getDestination());

        String originName = airportService.getAirportName(request.getOrigin());
        String destinationName = airportService.getAirportName(request.getDestination());

        if (originName.equals("Unknown Airport") || destinationName.equals("Unknown Airport")) {
            logger.warn("Invalid origin ({}) or destination ({}) code.", request.getOrigin(), request.getDestination());
            return ResponseEntity.badRequest().body("Invalid origin or destination code.");
        }

        if (originName.equalsIgnoreCase(destinationName)) {
            logger.warn("Origin and destination cannot be the same: {}", originName);
            return ResponseEntity.badRequest().body("Origin and destination cannot be the same.");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<FlightSearchResponse> flightResponses = flightService.searchFlights(request, pageable);

        flightResponses.forEach(flightResponse -> {
            flightResponse.setOriginAirportName(originName);
            flightResponse.setDestinationAirportName(destinationName);
        });

        logger.info("Flight search completed successfully.");
        return ResponseEntity.ok(flightResponses);
    }

    @PostMapping
    @Operation(summary = "Create Flight", description = "Create a new flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<Flight> createFlight(
            @Parameter(description = "Flight details", required = true) @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update Flight", description = "Update an existing flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    public ResponseEntity<Flight> updateFlight(
            @Parameter(description = "Flight ID", required = true) @PathVariable Long id,
            @Parameter(description = "Updated flight details", required = true) @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Flight", description = "Delete a flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteFlight(
            @Parameter(description = "Flight ID", required = true) @PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find Flight by ID", description = "Find a flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    public ResponseEntity<Flight> findFlightById(
            @Parameter(description = "Flight ID", required = true) @PathVariable Long id) throws FlightNotFoundException {
        return ResponseEntity.ok(flightService.findFlightById(id));
    }


    @GetMapping("/number/{flightNumber}")
    @Operation(summary = "Find Flight by Flight Number", description = "Find a flight by its flight number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content)
    })
    public ResponseEntity<Flight> findFlightByFlightNumber(
            @Parameter(description = "Flight number", required = true) @PathVariable String flightNumber) throws FlightNotFoundException {
        return ResponseEntity.ok(flightService.findFlightByFlightNumber(flightNumber));
    }


    @GetMapping
    @Operation(summary = "Find All Flights", description = "Retrieve all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flights retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))})
    })
    public ResponseEntity<List<Flight>> findAllFlights() {
        return ResponseEntity.ok(flightService.findAllFlights());
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Operation(
            summary = "Handle FlightNotFoundException",
            description = "Handle flight not found exception and return a 404 status."
    )
    public ResponseEntity<String> handleFlightNotFoundException(FlightNotFoundException ex) {
        logger.error("Flight not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
