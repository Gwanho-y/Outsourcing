package com.example.outsourcing.service;

import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.dto.UserCreateRequestDto;
import com.example.outsourcing.dto.UserCreateResponseDto;
import com.example.outsourcing.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserCreateResponseDto createUserService(UserCreateRequestDto requestDto) {
        //비밀번호가 형식에 맞는지 확인함
        String password = requestDto.getPassword();
        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("비밀번호에는 대문자가 최소 1개 포함되어야 합니다.");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("비밀번호에는 소문자가 최소 1개 포함되어야 합니다.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("비밀번호에는 숫자가 최소 1개 포함되어야 합니다.");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("비밀번호에는 특수문자가 최소 1개 포함되어야 합니다.");
        }
        //메일이 중복인지 확인함 (JPA에서 지원)
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        //비밀번호를 암호화한다
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        //user 생성시 암호화된 비밀번호로 세팅
        UserEntity user = new UserEntity(
                requestDto.getEmail(),
                encodedPassword,
                requestDto.getName()
        );
        //유저 데이터베이스에 저장
        UserEntity saveUserEntity = userRepository.save(user);
        return new UserCreateResponseDto(saveUserEntity);
    }
}
