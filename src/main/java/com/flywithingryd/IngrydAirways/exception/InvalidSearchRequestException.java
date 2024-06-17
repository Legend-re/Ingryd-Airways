package com.flywithingryd.IngrydAirways.exception;

public class InvalidSearchRequestException extends RuntimeException {
    public InvalidSearchRequestException(String message) {
        super(message);
    }
}
