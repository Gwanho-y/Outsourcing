package com.example.outsourcing.repository;

import com.example.outsourcing.entity.LogEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

/*
테이블 형태
로그id / 유저id / 이름 / 유형 / 내용 / 시간
 */
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    @NonNull
    List<LogEntity> findAll();
}
