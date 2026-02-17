package com.allobank.angky.rateaggregatorservice.exception;

public class InvalidResourceTypeException extends RuntimeException {
    public InvalidResourceTypeException(String resourceType) {
        super("Invalid resourceType: " + resourceType);
    }
}

