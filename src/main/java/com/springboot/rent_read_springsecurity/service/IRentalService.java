package com.springboot.rent_read_springsecurity.service;

import com.springboot.rent_read_springsecurity.dto.RentalDto;

public interface IRentalService {

    public RentalDto rentBook(Long bookId);
    public void returnBook(Long rentalId);

}
