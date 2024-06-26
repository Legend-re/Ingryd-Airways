package com.flywithingryd.IngrydAirways.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flywithingryd.IngrydAirways.dto.request.AircraftRequest;
import com.flywithingryd.IngrydAirways.service.AircraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.AIRCRAFT_CONTROLLER_ENDPOINT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AircraftRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private AircraftRestController aircraftRestController;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(aircraftRestController).build();
    }

    @Test
    public void testCreateAircraft() throws Exception {
        AircraftRequest validRequest = new AircraftRequest();
        validRequest.setModel("Boeing 747");
        validRequest.setYearManufactured(2020);
        validRequest.setSeatCapacity(300);

        doNothing().when(aircraftService).createAircraft(any(AircraftRequest.class));

        mockMvc.perform(post(AIRCRAFT_CONTROLLER_ENDPOINT + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated());

        verify(aircraftService, times(1)).createAircraft(any(AircraftRequest.class));
    }
}
