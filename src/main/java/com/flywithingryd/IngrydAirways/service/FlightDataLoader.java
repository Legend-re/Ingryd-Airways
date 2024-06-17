package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import com.flywithingryd.IngrydAirways.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
* Please Don't Delete this Class as
* it use to Manually Create and Load Aircraft to the Database(Use for Testing)
* As Controller Class for Aircraft didn't initially exist
* and it another alternative to postman testing for now.
*/

import java.util.List;

//@Component
//public class FlightDataLoader implements CommandLineRunner {
//    private final AircraftRepository aircraftRepository;
//
//    @Autowired
//    public FlightDataLoader(AircraftRepository aircraftRepository) {
//        this.aircraftRepository = aircraftRepository;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            List<Aircraft> aircraftList = List.of(
//                    new Aircraft("Boeing 747", 2024, "AC001", AircraftStatus.ACTIVE, 320, 20, 300), // Adjusted order
//                    new Aircraft("Airbus A300", 2024, "AC002", AircraftStatus.ACTIVE, 350, 30, 320)  // Added a new unique aircraft
//
//                    // Add more Aircraft instances
//            );
//
//            for (Aircraft aircraft : aircraftList) {
//                if (!aircraftRepository.existsByRegistrationNumber(aircraft.getRegistrationNumber())) {
//                    aircraftRepository.save(aircraft);
//                } else {
//                    // Log or handle duplicate
//                    System.out.println("Duplicate registration number: " + aircraft.getRegistrationNumber());
//                }
//            }
//    } catch(Exception ex){
//        System.out.println("An error occurred try to Load Date to the Repository" + ex.getMessage());
//    }
//    }
//}









