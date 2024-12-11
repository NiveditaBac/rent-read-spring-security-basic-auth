package com.springboot.rent_read_springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rent_read_springsecurity.dto.BookDto;
import com.springboot.rent_read_springsecurity.service.IBookService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(BookController.BOOK_API_ENDPOINT)
@Log4j2
public class BookController {

    public final static String BOOK_API_ENDPOINT = "/books";
    public final static String BOOK_API = "/{bookId}";
    
    @Autowired
    private IBookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        log.info("Book add request called : " + bookDto);
        BookDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping(BOOK_API)
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId){
        log.info("Book get request for ID: " + bookId);
        BookDto bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        log.info("Book get all request called");
        List<BookDto> bookDtos = bookService.getAllBooks();
        if(bookDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }   

    @PutMapping(BOOK_API)
    public ResponseEntity<BookDto> updateBook(@PathVariable Long bookId, @RequestBody BookDto bookDto){  
        log.info("Book update called for Book ID: " + bookId);
        return ResponseEntity.ok(bookService.updateBook(bookDto, bookId));
    }   


    @DeleteMapping(BOOK_API)
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId){
        log.info("Book delete called for Book ID: " + bookId);
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

}
