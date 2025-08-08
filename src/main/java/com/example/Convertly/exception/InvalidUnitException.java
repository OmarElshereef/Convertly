package com.example.Convertly.exception;

public class InvalidUnitException extends RuntimeException {
    public InvalidUnitException(String message) {
        super(message);
    }
}
