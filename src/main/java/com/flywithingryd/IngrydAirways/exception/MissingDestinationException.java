package com.flywithingryd.IngrydAirways.exception;

public class MissingDestinationException extends RuntimeException{
    public MissingDestinationException(String message){
        super(message);
    }
}
