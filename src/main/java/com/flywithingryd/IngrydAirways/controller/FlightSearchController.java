//package com.flywithingryd.IngrydAirways.controller;
//
//import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
//import com.flywithingryd.IngrydAirways.dto.response.FlightSearchResponse;
//import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
//import com.flywithingryd.IngrydAirways.service.AirportService;
//import com.flywithingryd.IngrydAirways.service.FlightService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/flights")
//@Validated
//@Tag(name = "Flight API", description = "API for searching flights")
//public class FlightSearchController {
//
//    private final FlightService flightService;
//    private final AirportService airportService;
//    private static final Logger logger = LoggerFactory.getLogger(FlightSearchController.class);
//
//    @Autowired
//    public FlightSearchController(FlightService flightService, AirportService airportService) {
//        this.flightService = flightService;
//        this.airportService = airportService;
//    }
//
//    @PostMapping("/search")
//    @Operation(
//            summary = "Search for flights",
//            description = "Search for flights based on origin, destination, and other criteria."
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Flight search successful"),
//            @ApiResponse(responseCode = "400", description = "Invalid origin or destination code")
//    })
//    public ResponseEntity<?> searchFlights(
//            @RequestBody @Valid FlightSearchRequest request,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        logger.info("Searching flights from {} to {}", request.getOrigin(), request.getDestination());
//
//        String originName = airportService.getAirportName(request.getOrigin());
//        String destinationName = airportService.getAirportName(request.getDestination());
//
//        if (originName.equals("Unknown Airport") || destinationName.equals("Unknown Airport")) {
//            logger.warn("Invalid origin ({}) or destination ({}) code.", request.getOrigin(), request.getDestination());
//            return ResponseEntity.badRequest().body("Invalid origin or destination code.");
//        }
//
//        if (originName.equalsIgnoreCase(destinationName)) {
//            logger.warn("Origin and destination cannot be the same: {}", originName);
//            return ResponseEntity.badRequest().body("Origin and destination cannot be the same.");
//        }
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<FlightSearchResponse> flightResponses = flightService.searchFlights(request, pageable);
//
//        flightResponses.forEach(flightResponse -> {
//            flightResponse.setOriginAirportName(originName);
//            flightResponse.setDestinationAirportName(destinationName);
//        });
//
//        logger.info("Flight search completed successfully.");
//        return ResponseEntity.ok(flightResponses);
//    }
//
//    @ExceptionHandler(FlightNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @Operation(
//            summary = "Handle FlightNotFoundException",
//            description = "Handle flight not found exception and return a 404 status."
//    )
//    public ResponseEntity<String> handleFlightNotFoundException(FlightNotFoundException ex) {
//        logger.error("Flight not found: {}", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//}
