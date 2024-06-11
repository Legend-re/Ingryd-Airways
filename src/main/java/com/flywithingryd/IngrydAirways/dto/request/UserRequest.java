package com.flywithingryd.IngrydAirways.dto.request;

import com.flywithingryd.IngrydAirways.model.Address;
import com.flywithingryd.IngrydAirways.model.enums.Gender;
import com.flywithingryd.IngrydAirways.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dob;
    private Gender gender;
    private Address address;
    private Role role;
}
