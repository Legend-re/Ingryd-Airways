package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Reservation reservation;

    public Itinerary() {
    }

    public Itinerary(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
