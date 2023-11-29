package com.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.config.TokenExpiredException;

@ControllerAdvice
public class GlobalExceptionExpiredHandler {
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
        return new ResponseEntity<>("Token has expired", HttpStatus.UNAUTHORIZED);
    }
}