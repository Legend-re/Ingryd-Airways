package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.AircraftRequest;
import com.flywithingryd.IngrydAirways.service.AircraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.AIRCRAFT_CONTROLLER_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(AIRCRAFT_CONTROLLER_ENDPOINT)
@Tag(name = "Aircraft Management", description = "API for creating and managing aircraft")
public class AircraftRestController {

    private final AircraftService aircraftService;

    @PostMapping("/create")
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
}
