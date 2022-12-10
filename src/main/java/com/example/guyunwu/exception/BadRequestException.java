package com.example.guyunwu.exception;

public class BadRequestException extends IllegalArgumentException{

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
