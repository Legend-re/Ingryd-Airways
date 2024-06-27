package com.flywithingryd.IngrydAirways.dto.request;

import com.flywithingryd.IngrydAirways.model.Passenger;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class ReservationRequest {

    private Long flightId;

    private Set<Passenger> passengers;

    private Timestamp timestamp;
}
