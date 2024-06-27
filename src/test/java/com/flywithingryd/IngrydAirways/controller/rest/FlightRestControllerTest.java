package com.flywithingryd.IngrydAirways.controller.rest;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.MANAGE_FLIGHT_CONTROLLER_ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.model.enums.SeatClass;
import com.flywithingryd.IngrydAirways.service.AirportService;
import com.flywithingryd.IngrydAirways.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FlightRestController.class})
@ExtendWith(SpringExtension.class)
class FlightRestControllerTest {
    @MockBean
    private AirportService airportService;

    @Autowired
    private FlightRestController flightRestController;

    @MockBean
    private FlightService flightService;

//     /**
//     * Method under test:
//     * {@link FlightRestController#handleFlightNotFoundException(FlightNotFoundException)}
//     */
//    @Test
//    void testHandleFlightNotFoundException() {
//        ResponseEntity<String> actualHandleFlightNotFoundExceptionResult = flightRestController
//                .handleFlightNotFoundException(new FlightNotFoundException("An error occurred"));
//        assertEquals("An error occurred", actualHandleFlightNotFoundExceptionResult.getBody());
//        assertEquals(404, actualHandleFlightNotFoundExceptionResult.getStatusCodeValue());
//        assertTrue(actualHandleFlightNotFoundExceptionResult.getHeaders().isEmpty());
//    }

//    /**
//     * Method under test:
//     * {@link FlightRestController#searchFlights(FlightSearchRequest, int, int)}
//     */
    @Test
    void testSearchFlights() throws Exception {
        when(airportService.getAirportName(Mockito.<String>any())).thenReturn("Airport Name");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(MANAGE_FLIGHT_CONTROLLER_ENDPOINT + "/search");
        MockHttpServletRequestBuilder paramResult = postResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder contentTypeResult = paramResult.param("size", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new FlightSearchRequest("Origin", "Destination", SeatClass.ECONOMY, 10, "2020-03-01")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(flightRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Origin and destination cannot be the same."));
    }

//    /**
//     * Method under test:
//     * {@link FlightRestController#searchFlights(FlightSearchRequest, int, int)}
//     */
    @Test
    void testSearchFlights2() throws Exception {
        when(airportService.getAirportName(Mockito.<String>any())).thenReturn("Unknown Airport");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(MANAGE_FLIGHT_CONTROLLER_ENDPOINT + "/search");
        MockHttpServletRequestBuilder paramResult = postResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder contentTypeResult = paramResult.param("size", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new FlightSearchRequest("Origin", "Destination", SeatClass.ECONOMY, 10, "2020-03-01")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(flightRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid origin or destination code."));
    }

//    /**
//     * Method under test:
//     * {@link FlightRestController#searchFlights(FlightSearchRequest, int, int)}
//     */
    @Test
    void testSearchFlights3() throws Exception {
        when(airportService.getAirportName(Mockito.<String>any())).thenReturn("Airport Name");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(MANAGE_FLIGHT_CONTROLLER_ENDPOINT + "/search");
        MockHttpServletRequestBuilder paramResult = postResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder contentTypeResult = paramResult.param("size", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new FlightSearchRequest("", "Destination", SeatClass.ECONOMY, 10, "2020-03-01")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(flightRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

//    /**
//     * Method under test:
//     * {@link FlightRestController#searchFlights(FlightSearchRequest, int, int)}
//     */
//    @Test
    void testSearchFlights4() throws Exception {
        when(airportService.getAirportName(Mockito.<String>any()))
                .thenThrow(new FlightNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(MANAGE_FLIGHT_CONTROLLER_ENDPOINT + "/search");
        MockHttpServletRequestBuilder paramResult = postResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder contentTypeResult = paramResult.param("size", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new FlightSearchRequest("Origin", "Destination", SeatClass.ECONOMY, 10, "2020-03-01")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(flightRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred"));
    }
}
