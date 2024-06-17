package com.flywithingryd.IngrydAirways.dto.request;

//import com.flywithingryd.IngrydAirways.config.FieldName;
import com.flywithingryd.IngrydAirways.model.enums.SeatClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FlightSearchRequest {
    @NotBlank(message = "Origin must not be blank")
    private String origin;

    @NotBlank(message = "Destination must not be blank")
    private String destination;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Travel Class must not be null")
    private SeatClass travelClass;

    @Min(value = 1, message = "Number of passengers must be at least 1")
    private int numberOfPassengers;

    @NotBlank(message = "Departure Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String departureDate;

    public FlightSearchRequest(
            @NotBlank(message = "Origin must not be blank") String origin,
            @NotBlank(message = "Destination must not be blank") String destination,
            @NotNull(message = "Travel Class must not be null") SeatClass travelClass,
            @Min(value = 1, message = "Number of passengers must be at least 1") int numberOfPassengers,
            @NotBlank(message = "Departure Date is required")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") String departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.travelClass = travelClass;
        this.numberOfPassengers = numberOfPassengers;
        this.departureDate = departureDate;
    }

    @Enumerated(EnumType.STRING)
    public void setTravelClass(@NotNull SeatClass travelClass) {
        this.travelClass = travelClass;
    }
}
