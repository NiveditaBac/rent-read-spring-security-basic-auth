package com.springboot.rent_read_springsecurity.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){}

    public UserNotFoundException(String message) {
        super(message);
    }

}
