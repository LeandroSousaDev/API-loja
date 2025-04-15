package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.user.UserEntity;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
