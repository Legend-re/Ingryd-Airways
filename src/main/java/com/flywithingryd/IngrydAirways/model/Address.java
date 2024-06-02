package com.flywithingryd.IngrydAirways.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;

    private String city;

    private String State;

    private String zipCode;

    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String city, String country, String state, String streetAddress, String zipCode) {
        this.city = city;
        this.country = country;
        State = state;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
