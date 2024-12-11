package com.springboot.rent_read_springsecurity.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.hamcrest.Matchers;

import com.springboot.rent_read_springsecurity.dto.BookDto;
import com.springboot.rent_read_springsecurity.service.IBookService;

@WebMvcTest(BookController.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IBookService bookService;

    private BookDto bookDto;

    private static final String BASE_URL = BookController.BOOK_API_ENDPOINT;    

    @BeforeEach
    void setUp(){
        bookDto = new BookDto();
        bookDto.setBookId(1L);
        bookDto.setTitle("Java Programming");
        bookDto.setAuthor("Theophilus Edet");
        bookDto.setGenre("Computer Programming");
        bookDto.setAvailabilityStatus(true);
    }

    @Test
    @DisplayName("Get Book by given Id")
    public void testBookById() throws Exception{
        Mockito.when(bookService.getBookById(1L)).thenReturn(bookDto);

        String requestBody = """
                                {
                                    "title": "Java Programming",
                                    "author": "Theophilus Edet",
                                    "genre": "Computer Programming",
                                    "availabilityStatus": "true"
                                }
                                """;

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Java Programming")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("Theophilus Edet")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.genre", Matchers.is("Computer Programming")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.availabilityStatus", Matchers.is(true)));

        Mockito.verify(bookService, Mockito.times(1)).getBookById(1L);
    }
} 