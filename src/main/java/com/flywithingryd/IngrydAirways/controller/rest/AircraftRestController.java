package com.flywithingryd.IngrydAirways.controller.rest;

import com.flywithingryd.IngrydAirways.dto.request.AircraftRequest;
import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import com.flywithingryd.IngrydAirways.service.AircraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.AIRCRAFT_CONTROLLER_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(AIRCRAFT_CONTROLLER_ENDPOINT)
@Tag(name = "Aircraft Management", description = "API for creating and managing aircraft")
public class AircraftRestController {

    private final AircraftService aircraftService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create Aircraft", description = "Create a new aircraft.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aircraft created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<Void> createAircraft(@Valid @RequestBody AircraftRequest request) {
        aircraftService.createAircraft(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{regNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get Aircraft", description = "Get aircraft by Registration Number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aircraft found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aircraft.class))}),
            @ApiResponse(responseCode = "404", description = "Aircraft not found",
                    content = @Content)
    })
    public ResponseEntity<Aircraft> getAircraftByRegistration(
            @Parameter(description = "Aircraft Registration Number", required = true) @PathVariable String regNumber) {
        return new ResponseEntity<>(aircraftService.getAircraft(regNumber), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get All Aircraft", description = "Retrieve all aircraft")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aircraft retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aircraft.class))})
    })
    public ResponseEntity<List<Aircraft>> getAllAircraft() {
        return new ResponseEntity<>(aircraftService.getAllAircraft(), HttpStatus.OK);
    }

    @PatchMapping("/{regNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Change Aircraft Status", description = "Change an existing aircraft's status by its registration number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aircraft Status Updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Flight.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aircraft not found",
                    content = @Content)
    })
    public ResponseEntity<Void> changeAircraftStatus(
            @Parameter(description = "Aircraft Registration Number", required = true) @PathVariable String regNumber,
            @Parameter(description = "Update aircraft status", required = true) @RequestParam AircraftStatus status) {
        aircraftService.toggleAircraftStatus(regNumber, status);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
