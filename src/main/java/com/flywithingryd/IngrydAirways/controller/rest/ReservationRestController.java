package com.flywithingryd.IngrydAirways.controller.rest;

import com.flywithingryd.IngrydAirways.dto.request.ReservationRequest;
import com.flywithingryd.IngrydAirways.model.Passenger;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.flywithingryd.IngrydAirways.controller.ApiEndpoints.RESERVATION_CONTROLLER_ENDPOINT;

@RestController
@RequestMapping(RESERVATION_CONTROLLER_ENDPOINT)
@Tag(name = "Reservation Management", description = "API for booking and managing Reservations")
public class ReservationRestController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make-reservation")
    private ResponseEntity<Reservation> saveReservation(@RequestBody ReservationRequest request) throws MessagingException {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }

    @GetMapping("/get-reservation")
    public ResponseEntity<Reservation> getReservationByReservationNum(@RequestParam String reservationNum){
        Reservation reservation = reservationService.getReservationByReservationNum(reservationNum);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping("/all-reservations")
    public ResponseEntity<List<Reservation>> findAllFlights() {
        return ResponseEntity.ok(reservationService.findAllFlights());
    }
    @PatchMapping("/cancel-reservation/{reservationNumber}")
    public ResponseEntity<Void> cancelReservationByReservationNum(@PathVariable String reservationNumber){
        reservationService.cancelReservationByReservationNum(reservationNumber);
        return ResponseEntity.noContent().build();

    }
}
