package com.springboot.rent_read_springsecurity.controller;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.rent_read_springsecurity.dto.BookDto;
import com.springboot.rent_read_springsecurity.dto.RentalDto;
import com.springboot.rent_read_springsecurity.dto.UserDto;
import com.springboot.rent_read_springsecurity.entity.Role;
import com.springboot.rent_read_springsecurity.mapper.BookMapper;
import com.springboot.rent_read_springsecurity.mapper.UserMapper;
import com.springboot.rent_read_springsecurity.service.IRentalService;

@ExtendWith(MockitoExtension.class)
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IRentalService rentalService;

    @InjectMocks
    private RentalController rentController;

    private RentalDto rentalDto;
    private UserDto userDto;
    private BookDto bookDto;

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.standaloneSetup(rentController).build();

        userDto = new UserDto(1L, "john.david@example.com", "password" , "John","David" , Role.ADMIN);
        bookDto = new BookDto(1L, "Java Programming", "Theophilus Edet", "computer programming", true);
        rentalDto = new RentalDto(1L, UserMapper.mapToUser(userDto), BookMapper.mapToBook(bookDto), LocalDateTime.now(), LocalDateTime.now().plusDays(7));
    }

    private static final String BASE_URL = RentalController.RENTAL_API_ENDPOINT;
    public final static String RENT_URL = RentalController.RENTAL_RENT_API;
    public final static String RETURN_URL = RentalController.RENTAL_RETURN_API;

    @Test
    @DisplayName("Rent Book for given user and book")
    public void testRentBook_ReturnsRentalDto_WhenBookIsRented() throws Exception {
        Long bookId = 1L;
        Mockito.when(rentalService.rentBook(bookId)).thenReturn(rentalDto);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + RENT_URL, bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
               .andExpect(MockMvcResultMatchers.status().isAccepted())
               .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId", Matchers.is(1)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.user", Matchers.is(userDto)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.book", Matchers.is(bookDto)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.rentedAt", Matchers.notNullValue()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.returnedAt", Matchers.notNullValue()));
    }

}
