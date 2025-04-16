package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.user.CreateUserDto;
import com.leandroSS.API_Loja.entities.user.UserEntity;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void register(CreateUserDto createUserDto) throws Exception {

        var user = userRepository.findByUsername(createUserDto.username());
        if (user.isPresent()) {
            throw  new Exception("Usuario ja existe");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(createUserDto.username());
        newUser.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        newUser.setRole(createUserDto.userType());

        userRepository.save(newUser);
    }
}






