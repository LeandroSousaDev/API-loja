package com.leandroSS.API_Loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcepitionHandler {

    @ExceptionHandler(InvalidLoginOrPassword.class)
    ResponseEntity<ErrorMessage> invalidLoginOrPassword(InvalidLoginOrPassword exception) {
        ErrorMessage threartResponse = new ErrorMessage(exception.getMessage(), "401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threartResponse);
    }

    @ExceptionHandler(UserAlreadyRegistered.class)
    ResponseEntity<ErrorMessage> userAlreadyRegistered(UserAlreadyRegistered exception) {
        ErrorMessage threartResponse = new ErrorMessage(exception.getMessage(), "409");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threartResponse);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<ErrorMessage> unauthorizedUser(UnauthorizedUser exception) {
        ErrorMessage threartResponse = new ErrorMessage(exception.getMessage(), "401");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threartResponse);
    }

}
