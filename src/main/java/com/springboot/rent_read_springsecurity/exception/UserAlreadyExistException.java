package com.springboot.rent_read_springsecurity.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(){}

    public UserAlreadyExistException(String message) {
        super(message);
    }

}
