package com.pragma.plazoleta_usuarios.domain.exceptions;

public class RoleNotFoundExceptionDomain extends RuntimeException{
    public RoleNotFoundExceptionDomain(String message) {
        super(message);
    }
}