package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Reservation reservation;


}
