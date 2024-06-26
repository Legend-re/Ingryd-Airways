package com.flywithingryd.IngrydAirways.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;

    private String city;

    private String state;

    private String zipCode;

    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Address(String city, String country, String state, String streetAddress, String zipCode) {
        this.city = city;
        this.country = country;
        this.state = state;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public Address(String street, String city, String state, String zipCode) {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
