package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "itinerary")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Passenger passenger;
    private Reservation reservation;

    @ManyToOne
    @JoinColumn()
    private Flight flight;

}
