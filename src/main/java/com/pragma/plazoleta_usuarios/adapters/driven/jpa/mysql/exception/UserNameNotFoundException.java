package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(String message) {
        super(message);
    }
}