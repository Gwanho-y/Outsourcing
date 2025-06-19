package com.example.outsourcing.dto.user;

import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.entity.UserRole;

public class UserCreateResponseDto {

    private Long id;
    private String email;
    private String name;
    private UserRole role;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public UserCreateResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.role = userEntity.getUserRole();
    }
}

