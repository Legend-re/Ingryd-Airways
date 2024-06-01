package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {
}