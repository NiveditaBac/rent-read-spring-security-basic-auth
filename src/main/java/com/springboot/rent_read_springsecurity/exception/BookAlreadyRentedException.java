package com.springboot.rent_read_springsecurity.exception;

public class BookAlreadyRentedException extends RuntimeException {

    public BookAlreadyRentedException() {}

    public BookAlreadyRentedException(String message) {
        super(message);
    }

}
