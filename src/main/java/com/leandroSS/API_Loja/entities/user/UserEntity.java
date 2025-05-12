package com.leandroSS.API_Loja.entities.user;

import com.leandroSS.API_Loja.entities.shopList.ShoppListEntity;
import com.leandroSS.API_Loja.entities.user.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private UserType role;

    @OneToMany(mappedBy = "user")
    private List<ShoppListEntity> shoppLists = new ArrayList<>();

    public UserEntity(String username, String password, UserType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }


}












