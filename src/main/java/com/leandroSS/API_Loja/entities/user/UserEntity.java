package com.leandroSS.API_Loja.entities.user;

import com.leandroSS.API_Loja.entities.shopList.ShoppList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<ShoppList> shoppLists = new ArrayList<>();

    public UserEntity(String username, String password, UserType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}












