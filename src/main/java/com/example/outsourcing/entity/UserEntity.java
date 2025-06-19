package com.example.outsourcing.entity;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 5)
    private String name;

    @Column(nullable = false)
    private boolean isDeleted = false;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public UserEntity(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    protected UserEntity() {
    }
}