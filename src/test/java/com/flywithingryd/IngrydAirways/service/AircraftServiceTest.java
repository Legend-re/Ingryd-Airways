package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.request.AircraftRequest;
import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.repository.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService airplaneService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateNewRegistrationNumber() {
        String regNumber = airplaneService.generateNewRegistrationNumber();
        assertNotNull(regNumber);
        assertTrue(regNumber.startsWith("GRYD"));
        assertEquals(7, regNumber.length()); // 4 letters + 3 digits
    }

    @Test
    void testCreateAircraft() {
        AircraftRequest request = new AircraftRequest();
        request.setModel("Boeing 747");
        request.setYearManufactured(2020);
        request.setSeatCapacity(300);

        when(aircraftRepository.existsByRegistrationNumber(anyString())).thenReturn(false);

        airplaneService.createAircraft(request);

        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
    }

    @Test
    void testCreateAircraftWithExistingRegNumber() {
        AircraftRequest request = new AircraftRequest();
        request.setModel("Airbus A380");
        request.setYearManufactured(2021);
        request.setSeatCapacity(500);

        when(aircraftRepository.existsByRegistrationNumber(anyString()))
                .thenReturn(true)
                .thenReturn(false); // First call returns true, second returns false

        airplaneService.createAircraft(request);

        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
    }
}