package com.springboot.rent_read_springsecurity.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springboot.rent_read_springsecurity.dto.RentalDto;
import com.springboot.rent_read_springsecurity.entity.Book;
import com.springboot.rent_read_springsecurity.entity.Rental;
import com.springboot.rent_read_springsecurity.entity.User;
import com.springboot.rent_read_springsecurity.exception.BookAlreadyRentedException;
import com.springboot.rent_read_springsecurity.exception.BookNotFoundException;
import com.springboot.rent_read_springsecurity.exception.LimitExceedException;
import com.springboot.rent_read_springsecurity.exception.UserNotFoundException;
import com.springboot.rent_read_springsecurity.mapper.RentalMapper;
import com.springboot.rent_read_springsecurity.repository.IBookRepository;
import com.springboot.rent_read_springsecurity.repository.IRentalRepository;
import com.springboot.rent_read_springsecurity.repository.IUserRepository;
import com.springboot.rent_read_springsecurity.service.IRentalService;

@Service
public class RentalServiceImpl implements IRentalService{

    private final int MAX_ACTIVE_RENTALS = 2;

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public RentalDto rentBook(Long bookId) {
        Book book = getBook(bookId);
        if(!book.isAvailabilityStatus()) {
            throw new BookAlreadyRentedException("Book is not available for rent.");
        }
        User user = getUser();               
        int numberOfRentals = rentalRepository.findByUserAndReturnedAtIsNull(user).size();
        if(numberOfRentals >= MAX_ACTIVE_RENTALS) {
            throw new LimitExceedException("User cannot rent more than " +  MAX_ACTIVE_RENTALS + " books at a time.");
        }
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        Rental saveRental = rentalRepository.save(rental);
        book.setAvailabilityStatus(false);
        bookRepository.save(book);
        return RentalMapper.mapToRentalDto(saveRental);
    }

    @Override
    public void returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                                        .orElseThrow(() -> new RuntimeException("Rental not found."));
        rental.setReturnedAt(LocalDateTime.now());
        Book book = rental.getBook();
        book.setAvailabilityStatus(true);
        bookRepository.save(book);
        rentalRepository.save(rental);
    }

    private Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));
    }

    private User getUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
