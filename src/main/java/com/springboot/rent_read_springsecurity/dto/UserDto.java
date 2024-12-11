package com.springboot.rent_read_springsecurity.dto;

import com.springboot.rent_read_springsecurity.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role = Role.USER;
}
