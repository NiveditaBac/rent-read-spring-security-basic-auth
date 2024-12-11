package com.springboot.rent_read_springsecurity.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rent_read_springsecurity.dto.BookDto;
import com.springboot.rent_read_springsecurity.entity.Book;
import com.springboot.rent_read_springsecurity.exception.AlreadyExistException;
import com.springboot.rent_read_springsecurity.exception.BookNotFoundException;
import com.springboot.rent_read_springsecurity.mapper.BookMapper;
import com.springboot.rent_read_springsecurity.repository.IBookRepository;
import com.springboot.rent_read_springsecurity.service.IBookService;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> BookMapper.mapToBookDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
       Book book = bookRepository.findById(bookId)
                                  .orElseThrow(() -> new BookNotFoundException("Book not found.")); 
        return BookMapper.mapToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookRepository.existsByTitle(bookDto.getTitle()))
            throw new AlreadyExistException("Book already exist with title : " + bookDto.getTitle());
        Book book = BookMapper.mapToBook(bookDto);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        return BookMapper.mapToBookDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long bookId) {
        Book book = bookRepository.findById(bookId)
                                  .orElseThrow(() -> new BookNotFoundException("Book not found.")); 
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getAuthor());
        return BookMapper.mapToBookDto(bookRepository.saveAndFlush(book));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.findById(bookId)
                      .orElseThrow(() -> new BookNotFoundException("Book not found with Id : " + bookId));
        bookRepository.deleteById(bookId);
    }
}