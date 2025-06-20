package com.example.outsourcing.controller;

import java.util.Date;

import com.example.outsourcing.dto.user.LoginRequestDto;
import com.example.outsourcing.dto.user.LoginStatusDto;
import com.example.outsourcing.dto.user.UserCreateRequestDto;
import com.example.outsourcing.dto.user.UserCreateResponseDto;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.repository.LogRepository;
import com.example.outsourcing.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "Zx2T9mVpQ8fT7uW2LpVzX1GsEjN7RmKdYbHa0oChSqZRlvIx4wPjMFt5DnUB3reOEg6CkR93NmaTqLsFi"
                    .getBytes(StandardCharsets.UTF_8));
    private final long EXPIRATION_TIME = 60 * 60 * 1000; // 토큰 유효기간 1시간
    private final LogRepository logRepository;

    public UserController(UserService userService, LogRepository logRepository) {
        this.userService = userService;
        this.logRepository = logRepository;
    }
    //회원가입
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUserAPI(@RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUserService(requestDto);
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    //회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = false) String authHeader, @RequestBody LoginRequestDto dto) {
        //회원 탈퇴 전에 토큰 검증
        try {
            String token = authHeader.substring(7);
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            userService.deleteUser(dto);
            return ResponseEntity.ok("회원탈퇴 완료");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("회원탈퇴 실패: " + e.getMessage());
        }
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        try {
            //유효성 검증 + 사용자 조회
            UserEntity user = userService.login(dto);
            //로그인 성공 시
            LoginStatusDto.LoginState.isLoggedIn = 1;
            //JWT 토큰 생성
            String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                    .compact();
            //응답 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body("로그인 성공");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인 실패: " + e.getMessage());
        }
    }
}
