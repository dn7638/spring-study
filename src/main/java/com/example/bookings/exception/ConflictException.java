package com.example.bookings.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
} 