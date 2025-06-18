package com.example.outsourcing.service;


import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.dto.user.LoginRequestDto;
import com.example.outsourcing.config.PasswordEncoder;
import com.example.outsourcing.dto.user.UserCreateRequestDto;
import com.example.outsourcing.dto.user.UserCreateResponseDto;
import com.example.outsourcing.global.exception.*;
import com.example.outsourcing.repository.LogRepository;
import com.example.outsourcing.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LogRepository logRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.logRepository = logRepository;
    }
    //회원가입 기능
    @Transactional
    public UserCreateResponseDto createUserService(UserCreateRequestDto requestDto) {
        //비밀번호가 형식에 맞는지 확인함
        String password = requestDto.getPassword();

        if (password.length() < 8) {
            throw new PasswordTooShortException();
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordMissingUppercaseException();
        }
        if (!password.matches(".*[a-z].*")) {
            throw new PasswordMissingLowercaseException();
        }
        if (!password.matches(".*\\d.*")) {
            throw new PasswordMissingDigitException();
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new PasswordMissingSpecialCharException();
        }
        //메일이 중복인지 확인함 (JPA에서 지원)
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException();
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
    //회원탈퇴 기능
    @Transactional
    public void deleteUser(LoginRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        if (user.isDeleted()) {
            throw new RuntimeException("이미 탈퇴된 사용자입니다.");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호 불일치로 탈퇴 실패");
        }
        if (!email.equals(user.getEmail())) {
            throw new RuntimeException("이메일 불일치로 탈퇴 실패");
        }

        user.setDeleted(true);
        userRepository.save(user);
    }
    //로그인 기능
    @Transactional
    public UserEntity login (LoginRequestDto requestDto) {
        //데이터 준비
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        //이메일로 사용자 찾기
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        //삭제된 유저인지 확인
        if (user.isDeleted()) {
            throw new RuntimeException("탈퇴된 사용자입니다.");
        }
        //비밀번호 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        //로그인에 성공하면

        //로그 생성
        LogEntity log = LogEntity.logFromLogIn(
                user,
                "작업 수정",
                "작업이 수정되었습니다."
        );
        logRepository.save(log);

        return user;
    }
}
