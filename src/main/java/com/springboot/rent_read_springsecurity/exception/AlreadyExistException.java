package com.springboot.rent_read_springsecurity.exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {}
    
    public AlreadyExistException(String message) {
        super(message);
    }

}