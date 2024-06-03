package com.flywithingryd.IngrydAirways.dto.response;

import com.flywithingryd.IngrydAirways.model.Address;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Gender gender;
    private Address address;
}
