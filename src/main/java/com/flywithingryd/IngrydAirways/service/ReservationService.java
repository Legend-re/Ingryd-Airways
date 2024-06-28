package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.CreateItineraryDTO;
import com.flywithingryd.IngrydAirways.dto.request.ReservationRequest;
import com.flywithingryd.IngrydAirways.exception.FlightNotFoundException;
import com.flywithingryd.IngrydAirways.exception.ReservationNotFoundException;
import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.model.Passenger;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
import com.flywithingryd.IngrydAirways.repository.FlightRepository;
import com.flywithingryd.IngrydAirways.repository.ReservationRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final MessageService messageService;
    private final ItineraryService itineraryService;


    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository, MessageService messageService, ItineraryService itineraryService) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
        this.messageService = messageService;
        this.itineraryService = itineraryService;
    }

    //Reservation Logic
    @Transactional
    public Reservation createReservation(ReservationRequest request) throws MessagingException {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));

        String reservationId = generateReservationId();

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationId);


        Set<Passenger> savedPassengers = new HashSet<>();
        for (Passenger passenger : request.getPassengers()) {
            String ticketId = generateTicketId();
            passenger.setTicketNumber(ticketId);
            passenger.setReservation(reservation);
            savedPassengers.add(passenger);
        }


        reservation.setPassengers(savedPassengers);
        reservation.setFlight(flight);
        reservation.setStatus(ReservationStatus.PENDING);


        Reservation savedReservation = reservationRepository.save(reservation);

        for (Passenger passenger : savedPassengers) {
            String email = passenger.getEmail();
            String firstName = passenger.getFirstName();
            messageService.reservationNotification(firstName, email, reservationId, reservation.getStatus().toString());
        }

        itineraryService.createItinerary(reservationId);

        return savedReservation;
    }


    // Generate a unique reservation ID
    private String generateReservationId() {
        StringBuilder reservationId = new StringBuilder();
        reservationId.append("RSV-");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randInt = random.nextInt(10);
            reservationId.append(randInt);
        }

        return reservationId.toString();
    }

    // Generate a unique ticket ID
    private String generateTicketId() {
        StringBuilder ticketId = new StringBuilder();
        ticketId.append("TICK-");

        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int randInt = random.nextInt(12);
            ticketId.append(randInt);
        }

        return ticketId.toString();
    }

    //Get Reservation by ReservationNum
    public Reservation getReservationByReservationNum(String reservationNum) throws ReservationNotFoundException {
        Optional<Reservation> reservation = reservationRepository.findAll()
                .stream().filter(f -> f.getReservationNumber().equals(reservationNum))
                .findFirst();
        return reservation.orElseThrow(()-> new ReservationNotFoundException("Reservation Number not found:" + reservationNum));
    }

    //Find All Reservation
    public List<Reservation> findAllFlights() {
        return reservationRepository.findAll();
    }

    //Cancel Reservation
    public void cancelReservationByReservationNum(String reservationNumber){
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
       logger.info("Cancelling Reservation {}, please wait...", reservationNumber);
        reservation.setStatus(ReservationStatus.CANCELLED);
       logger.info("Your Reservation is now Canceled {}", reservation);
        reservationRepository.save(reservation);
    }

    //find ReservationByID
    public Optional<Reservation> findReservationById(int id){
        return reservationRepository.findById(id);
    }

    //http://localhost:8085/api/reservation/get-reservation
    //http://localhost:8085/api/reservation/delete-reservation
    //http://localhost:8085/api/reservation/all-reservations
    //http://localhost:8085/api/reservation/cancel-reservation


}

