package com.leandroSS.API_Loja.exception;

public class UnauthorizedUser extends RuntimeException{

    public UnauthorizedUser(String message) {
        super(message);
    }
}
