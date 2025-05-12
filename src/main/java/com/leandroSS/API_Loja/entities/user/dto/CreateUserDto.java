package com.leandroSS.API_Loja.entities.user.dto;

import com.leandroSS.API_Loja.entities.user.UserType;

public record CreateUserDto(String username, String password, UserType userType) {
}
