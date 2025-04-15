package com.leandroSS.API_Loja.entities.user;

public record CreateUserDto(String username, String password, UserType userType) {
}
