package com.example.outsourcing.repository;

import com.example.outsourcing.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
테이블 형태
로그id / 유저id / 이름 / 유형 / 내용 / 시간
 */
@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findAllByUser_UserId(Long userId);     // userId로 조회
    List<LogEntity> findAllByName(String name);            // name으로 조회
    List<LogEntity> findAllByActType(String actType);      // actType으로 조회
}
