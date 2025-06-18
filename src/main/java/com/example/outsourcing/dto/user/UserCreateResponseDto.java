package com.example.outsourcing.dto.user;

import com.example.outsourcing.entity.UserEntity;

public class UserCreateResponseDto {

    private Long id;
    private String email;
    private String name;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserCreateResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
    }
}

