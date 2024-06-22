package com.flywithingryd.IngrydAirways.controller;

import com.flywithingryd.IngrydAirways.dto.request.UserRequest;
import com.flywithingryd.IngrydAirways.dto.response.UserResponse;
import com.flywithingryd.IngrydAirways.exception.ReservationNotFoundException;
import com.flywithingryd.IngrydAirways.model.Passenger;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.service.ReservationService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/created")
    private ResponseEntity<List<Reservation>> saveReservation(List<Passenger> passengers) throws MessagingException {
        return ResponseEntity.ok(reservationService.createReservation(passengers));
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
    @DeleteMapping("/cancel-reservation/{reservationNumber}")
    public ResponseEntity<Void> deleteReservationByReservationNum(@PathVariable String deleteReservation){
        logger.info("Cancelling Reservation, please wait... {}", deleteReservation);
        reservationService.deleteReservationByReservationNum(deleteReservation);
        logger.info("Your Reservation is now Canceled {}", deleteReservation);
        return ResponseEntity.noContent().build();

    }
}
