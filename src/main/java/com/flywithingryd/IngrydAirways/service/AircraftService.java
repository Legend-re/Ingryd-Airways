package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.AircraftRequest;
import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import com.flywithingryd.IngrydAirways.repository.AircraftRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AircraftService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String PREFIX = "GRYD";
    private static final Integer BUSINESS_CLASS_SEATS = 50;

    private final AircraftRepository aircraftRepository;

    String generateNewRegistrationNumber() {
        StringBuilder registrationNumber = new StringBuilder(PREFIX);

        int randomNumber = new Random().nextInt(999) + 1;
        registrationNumber.append(String.format("%03d", randomNumber));

        return registrationNumber.toString();
    }

    public void createAircraft(AircraftRequest request) {
        logger.info("Processing create new Aircraft");
        Aircraft aircraft = new Aircraft();
        String regNumber;

        do{
            regNumber = generateNewRegistrationNumber();
        }
        while (aircraftRepository.existsByRegistrationNumber(regNumber));

        aircraft.setModel(request.getModel());
        aircraft.setYearManufactured(request.getYearManufactured());
        aircraft.setSeatCapacity(request.getSeatCapacity());
        aircraft.setStatus(AircraftStatus.ACTIVE);
        aircraft.setRegistrationNumber(regNumber);
        aircraft.setBusinessClassSeats(BUSINESS_CLASS_SEATS);
        aircraft.setEconomyClassSeats(request.getSeatCapacity() - BUSINESS_CLASS_SEATS);

        aircraftRepository.save(aircraft);
        logger.info("Successfully created aircraft {}", regNumber);
    }
}
