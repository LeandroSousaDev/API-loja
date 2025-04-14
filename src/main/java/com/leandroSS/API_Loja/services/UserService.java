package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.user.UserEntity;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findUser(String login) {
        var user = this.userRepository.findByUsername(login);
        return user;
    }

}






