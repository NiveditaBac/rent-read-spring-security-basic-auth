package com.springboot.rent_read_springsecurity.mapper;

import com.springboot.rent_read_springsecurity.dto.BookDto;
import com.springboot.rent_read_springsecurity.entity.Book;

public class BookMapper {

    public static Book mapToBook(BookDto bookDto){
        Book book = new Book(
            bookDto.getBookId(),
            bookDto.getTitle(),
            bookDto.getAuthor(),
            bookDto.getGenre(),
            true
        );
        return book;
    }

    public static BookDto mapToBookDto(Book book){
        BookDto bookDto = new BookDto(
            book.getBookId(),
            book.getTitle(),
            book.getAuthor(),
            book.getGenre(),
            true
        );
        return bookDto;
    }
}
