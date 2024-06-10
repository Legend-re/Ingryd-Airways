package com.flywithingryd.IngrydAirways.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity(name ="passenger" )
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private Date dob;
    private Address address;
    private int ticketNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Passenger(){};
    public Passenger(int id, String firstName, String lastName,
                     String email, Date dob, Address address,
                     Gender gender, int ticketNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.ticketNumber = ticketNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getEmail() {
        return email;
    }
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return "id:"+getId() + "FirstName:" + getFirstName() + "LastName" + getLastName()
                + "Email" + getEmail() + "Ticket_ID:" + getTicketNumber() + "Gender:"+getId()
                + "Address:" + getAddress() + "DOB" + getDob() ;
    }
}