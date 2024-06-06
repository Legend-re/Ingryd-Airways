package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.enums.FlightStatus;
import com.flywithingryd.IngrydAirways.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightRestController {

    private final FlightService flightService;

    public FlightRestController(FlightService flightService){
        this.flightService = flightService;
    }

    @PostMapping("/createFlight")
    public ResponseEntity<Flight> createFlight(@RequestBody @Valid Flight flight){
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    @GetMapping("/findFlightById/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable long id){
        return new ResponseEntity<>(flightService.findFlightById(id), HttpStatus.OK);
    }

    @GetMapping("/flightNumber")
    public ResponseEntity<Flight> findFlightByFlightNumber(@RequestParam String flightNumber){
        return new ResponseEntity<>(flightService.findFlightByFlightNumber(flightNumber), HttpStatus.OK);
    }

    @GetMapping("/allFlight")
    public ResponseEntity<List<Flight>> findAllFlights(){
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK) ;
    }

    @PutMapping("/updateFlight/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody @Valid Flight flight, @PathVariable long id){
        return new ResponseEntity<>(flightService.updateFlight(flight, id), HttpStatus.OK);
    }

}
