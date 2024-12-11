package com.springboot.rent_read_springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private boolean availabilityStatus = true;

}
