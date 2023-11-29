package com.example.exception;


import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String message) {
        super(message);
    }

    public ExpiredTokenException(String message, Throwable cause) {
        super(message, cause);
    }
    public HttpStatus getStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}