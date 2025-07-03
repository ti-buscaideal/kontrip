package com.example.kontrip.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(ApiException.class);

    public ApiException(String message) {
        super(message);

        logger.error("Exceção lançada: {}", message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);

        logger.error("Exceção lançada: {}, Cause: {}", message, cause.getMessage());
    }

}
