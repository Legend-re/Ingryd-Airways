package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Aircraft;
import com.flywithingryd.IngrydAirways.model.enums.AircraftStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);

    Optional<Aircraft> findByRegistrationNumber(String registrationNumber);

    Optional<Aircraft> findByRegistrationNumberAndStatus(String registrationNumber, AircraftStatus status);
}
