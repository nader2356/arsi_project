package com.example.exception;

public class DuplicateSubscriptionException extends  RuntimeException{
    public DuplicateSubscriptionException(String message)
     {super(message);}
}