package com.springboot.rent_read_springsecurity.mapper;

import com.springboot.rent_read_springsecurity.dto.UserDto;
import com.springboot.rent_read_springsecurity.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto){
        User user = new User(
            userDto.getUserId(), 
            userDto.getEmail(), 
            userDto.getPassword(), 
            userDto.getFirstName(), 
            userDto.getLastName(), 
            userDto.getRole());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
            user.getUserId(),
            user.getEmail(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole()
        );
        return userDto;
    }
}