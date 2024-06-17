package com.flywithingryd.IngrydAirways.exception;

public class MissingOriginException extends RuntimeException{
    public MissingOriginException(String message){
        super(message);
    }
}
