package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.user.dto.CreateUserDto;
import com.leandroSS.API_Loja.entities.user.UserEntity;
import com.leandroSS.API_Loja.exception.UserAlreadyRegistered;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(CreateUserDto createUserDto) {

        var user = userRepository.findByUsername(createUserDto.username());
        if (user.isPresent()) {
            throw  new UserAlreadyRegistered("Usuario ja existe");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(createUserDto.password());

        UserEntity newUser = new UserEntity(
                createUserDto.username(),
                encryptedPassword,
                createUserDto.userType());

        userRepository.save(newUser);
    }

    public List<UserEntity> listUser() {
        return this.userRepository.findAll();
    }

}
