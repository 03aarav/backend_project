package com.example.demo.Exceptions;

class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
