package com.example.outsourcing.controller;

import com.example.outsourcing.dto.UserCreateRequestDto;
import com.example.outsourcing.dto.UserCreateResponseDto;
import com.example.outsourcing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUserAPI(@RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUserService(requestDto);
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
