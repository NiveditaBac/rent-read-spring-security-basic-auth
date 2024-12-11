package com.springboot.rent_read_springsecurity.dto;


import com.springboot.rent_read_springsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    String message;
    User userDetails;

}
