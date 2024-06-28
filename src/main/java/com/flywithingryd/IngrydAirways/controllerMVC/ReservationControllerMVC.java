package com.flywithingryd.IngrydAirways.controllerMVC;

import com.flywithingryd.IngrydAirways.dto.request.ReservationRequest;
import com.flywithingryd.IngrydAirways.model.Passenger;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.service.ReservationService;
import com.flywithingryd.IngrydAirways.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ReservationControllerMVC {

    private final ReservationService reservationService;

    public ReservationControllerMVC(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String showReservationPage(){

        return "reservation_page";
    }

    @PostMapping("/create-reservation")
    public String createReservation(@ModelAttribute ReservationRequest request) throws MessagingException {
        reservationService.createReservation(request);
        return "redirect:/api/reservation-created";
    }

    @GetMapping("/reservation-created")
    public String showReservationSuccessPage(){

        return "reservation_success";
    }
}
