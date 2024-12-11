package com.springboot.rent_read_springsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rent_read_springsecurity.dto.RentalDto;
import com.springboot.rent_read_springsecurity.service.IRentalService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(RentalController.RENTAL_API_ENDPOINT)
@Log4j2
public class RentalController {

    public final static String RENTAL_API_ENDPOINT = "/rentals";
    public final static String RENTAL_RENT_API = "/books/{bookId}/rent";
    public final static String RENTAL_RETURN_API = "/books/{rentalId}/return";

    @Autowired
    private IRentalService rentalService;

    @PostMapping(RENTAL_RENT_API)
    public ResponseEntity<RentalDto> rentBook(@PathVariable Long bookId) {
        log.info("Book rent endpoint called for Book ID: " + bookId);
        RentalDto rentalDto = rentalService.rentBook(bookId);
        return new ResponseEntity<>(rentalDto, HttpStatus.ACCEPTED);
    }   

    @PostMapping(RENTAL_RETURN_API)
    public ResponseEntity<Void> returnBook(@PathVariable Long rentalId) {
        log.info("Book rent endpoint called for Rental ID: " + rentalId);
        rentalService.returnBook(rentalId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}