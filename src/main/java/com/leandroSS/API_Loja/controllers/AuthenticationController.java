package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.user.CreateUserDto;
import com.leandroSS.API_Loja.entities.user.LoginRequest;
import com.leandroSS.API_Loja.entities.user.LoginResponse;
import com.leandroSS.API_Loja.services.TokenService;
import com.leandroSS.API_Loja.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            var loginUser = this.tokenService.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(loginUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
            this.userService.register(createUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}


















