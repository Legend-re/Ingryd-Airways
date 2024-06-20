package com.flywithingryd.IngrydAirways.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateItineraryDTO {
    @NotBlank
    private String reservationNumber;
}
