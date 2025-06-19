package com.example.outsourcing.dto.user;

import com.example.outsourcing.entity.UserRole;

public class UserCreateRequestDto {

    private String email;
    private String password;
    private String name;
    private UserRole role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public UserCreateRequestDto() {
    }
}
