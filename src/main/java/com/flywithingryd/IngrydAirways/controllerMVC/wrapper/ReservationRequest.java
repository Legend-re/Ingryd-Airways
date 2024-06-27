package com.flywithingryd.IngrydAirways.controllerMVC.wrapper;

import com.flywithingryd.IngrydAirways.model.Passenger;
import lombok.Data;

import java.util.List;
@Data
public class ReservationRequest {
    private List<Passenger> passengers;
    private String destinationCode;
    private String originCode;
}
