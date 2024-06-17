package com.flywithingryd.IngrydAirways.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequest {
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Length(min = 3)
    private String password;
}
