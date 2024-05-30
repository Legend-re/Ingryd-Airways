package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
