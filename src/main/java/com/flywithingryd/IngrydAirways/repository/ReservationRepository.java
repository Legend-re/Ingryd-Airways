package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>, JpaSpecificationExecutor<Reservation> {

   Reservation findByReservationNumber(String reservationNumber);
   void deleteByReservationNumber(String reservationNumber);
}