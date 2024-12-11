package com.springboot.rent_read_springsecurity.exception;

public class RentalNotFoundException extends RuntimeException{

    public RentalNotFoundException(){}

    public RentalNotFoundException(String message) {
        super(message);
    }

}
