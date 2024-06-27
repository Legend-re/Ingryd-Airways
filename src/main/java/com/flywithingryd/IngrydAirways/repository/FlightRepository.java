package com.flywithingryd.IngrydAirways.repository;


import com.flywithingryd.IngrydAirways.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByOriginCode(String originCode);
}


