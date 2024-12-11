package com.springboot.rent_read_springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.rent_read_springsecurity.dto.LoginResponse;
import com.springboot.rent_read_springsecurity.dto.UserDto;
import com.springboot.rent_read_springsecurity.entity.User;
import com.springboot.rent_read_springsecurity.exception.UserAlreadyExistException;
import com.springboot.rent_read_springsecurity.exception.UserNotFoundException;
import com.springboot.rent_read_springsecurity.mapper.UserMapper;
import com.springboot.rent_read_springsecurity.repository.IUserRepository;
import com.springboot.rent_read_springsecurity.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        return UserMapper.mapToUserDto(userRepository.save(user));
       
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public LoginResponse login() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User not found with Email ID : " + email));
        return new LoginResponse("User successfully logged in", user);
    }

}