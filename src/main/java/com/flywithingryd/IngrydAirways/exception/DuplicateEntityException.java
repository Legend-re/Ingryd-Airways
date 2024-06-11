package com.flywithingryd.IngrydAirways.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateEntityException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(DuplicateEntityException.class);

    public DuplicateEntityException(String message) {
        super(message);
        logger.error("DuplicateEntityException: {}", message);
    }
}