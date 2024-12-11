package com.springboot.rent_read_springsecurity.dto;

import java.time.LocalDateTime;

import com.springboot.rent_read_springsecurity.entity.Book;
import com.springboot.rent_read_springsecurity.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    private Long rentalId;
    private User user;
    private Book book;
    private LocalDateTime rentedAt = LocalDateTime.now();
    private LocalDateTime returnedAt;
}
