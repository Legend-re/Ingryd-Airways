package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.exception.ReservationNotFoundException;
import com.flywithingryd.IngrydAirways.model.Passenger;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.model.enums.ReservationStatus;
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
    private final MessageService messageService;


    public ReservationService(ReservationRepository reservationRepository, MessageService messageService) {
        this.reservationRepository = reservationRepository;
        this.messageService = messageService;

    }

    //Reservation Logic
    @Transactional
    public Reservation createReservation(List<Passenger> passengers) throws MessagingException {
        String reservationId = generateReservationId();

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationId);


        List<Passenger> savedPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            String ticketId = generateTicketId();
            passenger.setTicketNumber(ticketId);
            passenger.setReservations(reservation);
            savedPassengers.add(passenger);
        }


        reservation.setPassenger(savedPassengers);
        reservation.setStatus(ReservationStatus.PENDING);


        Reservation savedReservation = reservationRepository.save(reservation);

        for (Passenger passenger : savedPassengers) {
            String email = passenger.getEmail();
            String firstName = passenger.getFirstName();
            messageService.reservationNotification(firstName, email, reservationId, reservation.getStatus().toString());
        }

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
    public void cancelReservationByReservationNum(String reservationNumber, Reservation cancelReservation){
        Reservation user = reservationRepository.findByReservationNumber(reservationNumber);
       logger.info("Cancelling Reservation, please wait... {}", cancelReservation);
        user.setStatus(ReservationStatus.CANCELLED);
       logger.info("Your Reservation is now Canceled {}", cancelReservation);
        reservationRepository.save(user);
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

