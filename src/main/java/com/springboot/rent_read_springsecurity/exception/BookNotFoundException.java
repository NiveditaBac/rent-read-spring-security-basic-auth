package com.springboot.rent_read_springsecurity.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){}

    public BookNotFoundException(String message) {
        super(message);
    }


}
