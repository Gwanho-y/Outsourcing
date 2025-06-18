package com.example.outsourcing.repository;

import com.example.outsourcing.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //이메일 기준으로 사용자 조회
    Optional<UserEntity> findByEmail(String email);
    //회원가입시 사용할 기능
    boolean existsByEmail(String email);
}