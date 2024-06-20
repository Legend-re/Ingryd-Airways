package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
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

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerary itinerary)) return false;
        return id == itinerary.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
