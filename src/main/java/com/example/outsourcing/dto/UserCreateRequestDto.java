package com.example.outsourcing.dto;

public class UserCreateRequestDto {

    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserCreateRequestDto() {
    }
}
