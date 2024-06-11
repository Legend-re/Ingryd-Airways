package com.flywithingryd.IngrydAirways.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrongCredentialsException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(WrongCredentialsException.class);

    public WrongCredentialsException(String message) {
        super(message);
        logger.error("WrongCredentialsException: {}", message);
    }
}