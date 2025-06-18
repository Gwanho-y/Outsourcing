package com.example.outsourcing.repository;

import com.example.outsourcing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 있다고 가정, 커스텀 추가 가능
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}