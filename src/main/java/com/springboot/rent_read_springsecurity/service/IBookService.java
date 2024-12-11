package com.springboot.rent_read_springsecurity.service;

import java.util.List;
import com.springboot.rent_read_springsecurity.dto.BookDto;

public interface IBookService {

    public List<BookDto> getAllBooks();

    public BookDto getBookById(Long bookId);

    public BookDto createBook(BookDto bookDto);

    public BookDto updateBook(BookDto bookDto, Long bookId);
    
    public void deleteBook(Long bookId);
}
