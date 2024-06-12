package com.flywithingryd.IngrydAirways.exception;

public class ErrorResponse extends RuntimeException {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

