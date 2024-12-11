package com.springboot.rent_read_springsecurity.service;

import com.springboot.rent_read_springsecurity.dto.LoginResponse;
import com.springboot.rent_read_springsecurity.dto.UserDto;

public interface IUserService {

    public UserDto registerUser(UserDto userDto);
    
    public UserDto getUserByEmail(String email);

    public LoginResponse login();
}
