package com.leandroSS.API_Loja.exception;

public class UserAlreadyRegistered extends RuntimeException{

    public UserAlreadyRegistered(String message) {
        super(message);
    }
}
