package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.user.CreateUserDto;
import com.leandroSS.API_Loja.entities.user.LoginRequest;
import com.leandroSS.API_Loja.entities.user.LoginResponse;
import com.leandroSS.API_Loja.entities.user.UserEntity;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtEncoder jwtEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Useario ou senha invalido");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scope = user.get().getRole();

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();


        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto createUserDto) throws BadRequestException {

        var user = userRepository.findByUsername(createUserDto.username());
        if (user.isPresent()) {
            throw  new BadRequestException("Usuario ja existe");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(createUserDto.username());
        newUser.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        newUser.setRole(createUserDto.userType());

        userRepository.save(newUser);

        ResponseEntity.ok().build();

        return ResponseEntity.ok().build();
    }
}


















