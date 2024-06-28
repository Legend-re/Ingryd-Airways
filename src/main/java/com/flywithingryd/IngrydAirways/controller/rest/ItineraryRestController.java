package com.flywithingryd.IngrydAirways.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.flywithingryd.IngrydAirways.dto.CreateItineraryDTO;
import com.flywithingryd.IngrydAirways.dto.ItineraryDTO;
import com.flywithingryd.IngrydAirways.service.ItineraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.ITINERARY_CONTROLLER_ENDPOINT;

@RestController
@RequestMapping(ITINERARY_CONTROLLER_ENDPOINT)
@Tag(name = "Itinerary Management", description = "API for managing Itinerary")
public class ItineraryRestController {
    private final ItineraryService itineraryService;

    public ItineraryRestController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get itinerary by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the itinerary",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItineraryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Itinerary not found", content = @Content)
    })
    public ResponseEntity<ItineraryDTO> getItinerary(@PathVariable int id) {
        ItineraryDTO itineraryDTO = itineraryService.getItineraryById(id);
        return ResponseEntity.ok(itineraryDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing itinerary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the itinerary",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItineraryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Itinerary not found", content = @Content)
    })
    public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable int id, @Validated @RequestBody CreateItineraryDTO createItineraryDTO) {
        ItineraryDTO itineraryDTO = itineraryService.updateItinerary(id, createItineraryDTO);
        return ResponseEntity.ok(itineraryDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an itinerary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the itinerary"),
            @ApiResponse(responseCode = "404", description = "Itinerary not found", content = @Content)
    })
    public ResponseEntity<Void> deleteItinerary(@PathVariable int id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.noContent().build();
    }
}



//    @PostMapping
//    @Operation(summary = "Create a new itinerary")
//    @ApiResponse(responseCode = "201", description = "Itinerary created",
//            content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ItineraryDTO.class)))
//    public ResponseEntity<ItineraryDTO> createItinerary(@Validated @RequestParam String request) {
//        ItineraryDTO itineraryDTO = itineraryService.createItinerary(request);
//        return new ResponseEntity<>(itineraryDTO, HttpStatus.CREATED);
//    }





//@RestController
//@RequestMapping("/api/itineraries")
//public class ItineraryController {
//
//    private final ItineraryService itineraryService;
//
//    public ItineraryController(ItineraryService itineraryService) {
//        this.itineraryService = itineraryService;
//    }
//
//
//    @PostMapping
//    public ResponseEntity<ItineraryDTO> createItinerary(@Validated @RequestBody CreateItineraryDTO createItineraryDTO) {
//        ItineraryDTO itineraryDTO = itineraryService.createItinerary(createItineraryDTO);
//        return new ResponseEntity<>(itineraryDTO, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ItineraryDTO> getItinerary(@PathVariable int id) {
//        ItineraryDTO itineraryDTO = itineraryService.getItineraryById(id);
//        return ResponseEntity.ok(itineraryDTO);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable int id, @Validated @RequestBody CreateItineraryDTO createItineraryDTO) {
//        ItineraryDTO itineraryDTO = itineraryService.updateItinerary(id, createItineraryDTO);
//        return ResponseEntity.ok(itineraryDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteItinerary(@PathVariable int id) {
//        itineraryService.deleteItinerary(id);
//        return ResponseEntity.noContent().build();
//    }
//}
