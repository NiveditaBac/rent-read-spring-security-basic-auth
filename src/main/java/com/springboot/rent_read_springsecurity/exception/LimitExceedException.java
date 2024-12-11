package com.springboot.rent_read_springsecurity.exception;

public class LimitExceedException extends RuntimeException{

    public LimitExceedException(){}

    public LimitExceedException(String message) {
        super(message);
    }

}
