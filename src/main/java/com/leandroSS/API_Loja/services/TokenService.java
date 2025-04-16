package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.user.LoginRequest;
import com.leandroSS.API_Loja.entities.user.LoginResponse;
import com.leandroSS.API_Loja.entities.user.UserType;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtEncoder jwtEncoder;


    public LoginResponse login(LoginRequest loginRequest) throws Exception {

        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new Exception("Useario ou senha invalido");
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

        return new LoginResponse(jwtValue, expiresIn);
    }

    public Boolean isAdmin(JwtAuthenticationToken token) throws Exception {

        var user = this.userRepository.findById(Long.valueOf(token.getName()));

        if (!user.get().getRole().equals(UserType.ADMIN)) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean isUser(JwtAuthenticationToken token) throws Exception {

        var user = this.userRepository.findById(Long.valueOf(token.getName()));

        if (!user.get().getRole().equals(UserType.USER)) {
            return false;
        } else {
            return true;
        }

    }
}





















