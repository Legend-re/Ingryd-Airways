package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.dto.CreateItineraryDTO;
import com.flywithingryd.IngrydAirways.dto.ItineraryDTO;
import com.flywithingryd.IngrydAirways.exception.ItineraryNotFoundException;
import com.flywithingryd.IngrydAirways.exception.ReservationNotFoundException;
import com.flywithingryd.IngrydAirways.model.Itinerary;
import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.repository.ItineraryRepository;
import com.flywithingryd.IngrydAirways.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
     private final ReservationRepository reservationRepository;

    public ItineraryService(ItineraryRepository itineraryRepository, ReservationRepository reservationRepository) {
        this.itineraryRepository = itineraryRepository;
        this.reservationRepository = reservationRepository;
    }

    @CachePut(value = "itineraries", key = "#result.id")
    public ItineraryDTO createItinerary(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found for number: " + reservationNumber);
        }
        Itinerary itinerary = new Itinerary(reservation);
        Itinerary savedItinerary = itineraryRepository.save(itinerary);
        return toDTO(savedItinerary);
    }

    @Cacheable(value = "itineraries", key = "#id")
    public ItineraryDTO getItineraryById(int id) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ItineraryNotFoundException("Itinerary not found for ID: " + id));
        return toDTO(itinerary);
    }

    @CachePut(value = "itineraries", key = "#id")
    public ItineraryDTO updateItinerary(int id, CreateItineraryDTO createItineraryDTO) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ItineraryNotFoundException("Itinerary not found for ID: " + id));

        Reservation reservation = reservationRepository.findByReservationNumber(createItineraryDTO.getReservationNumber());
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found for number: " + createItineraryDTO.getReservationNumber());
        }
        itinerary.setReservation(reservation);
        Itinerary updatedItinerary = itineraryRepository.save(itinerary);
        return toDTO(updatedItinerary);
    }

    @CacheEvict(value = "itineraries", key = "#id")
    public void deleteItinerary(int id) {
        if (!itineraryRepository.existsById(id)) {
            throw new ItineraryNotFoundException("Itinerary not found for ID: " + id);
        }
        itineraryRepository.deleteById(id);
    }

    private ItineraryDTO toDTO(Itinerary itinerary) {
        ItineraryDTO dto = new ItineraryDTO();
        dto.setId(itinerary.getId());
        dto.setReservationNumber(itinerary.getReservation().getReservationNumber());
        dto.setFlightNumber(itinerary.getReservation().getFlight().getFlightNumber());
        return dto;
    }
}