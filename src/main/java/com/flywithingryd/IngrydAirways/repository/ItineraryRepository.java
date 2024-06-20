package com.flywithingryd.IngrydAirways.repository;

import com.flywithingryd.IngrydAirways.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}
