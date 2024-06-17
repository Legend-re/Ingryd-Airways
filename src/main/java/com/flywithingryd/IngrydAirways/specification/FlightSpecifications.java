package com.flywithingryd.IngrydAirways.specification;

import com.flywithingryd.IngrydAirways.model.Flight;
import com.flywithingryd.IngrydAirways.dto.request.FlightSearchRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class FlightSpecifications {
    public static Specification<Flight> searchFlights(FlightSearchRequest request) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (request.getOrigin() != null && !request.getOrigin().isEmpty()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("originCode"), request.getOrigin()));
            }

            if (request.getDestination() != null && !request.getDestination().isEmpty()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("destinationCode"), request.getDestination()));
            }

            if (request.getDepartureDate() != null) {
                try {
                    LocalDateTime departureDate = LocalDateTime.parse(request.getDepartureDate());
                    predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("departureDate"), departureDate));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid date format for departure date.");
                }
            }

            predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("travelClass"), request.getTravelClass()));

            return predicates;
        };
    }
}
