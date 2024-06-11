package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
