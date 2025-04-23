package com.leandroSS.API_Loja.exception;

public class InvalidLoginOrPassword extends RuntimeException{

    public InvalidLoginOrPassword(String message) {
        super(message);
    }
}
