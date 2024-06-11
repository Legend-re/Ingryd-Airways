package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}
