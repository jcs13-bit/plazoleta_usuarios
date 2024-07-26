package com.pragma.plazoleta_usuarios.domain.exceptions;

public class ConstantsDomain {
    private ConstantsDomain() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE = "User not found with email: ";

    public static final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "Invalid Password";


    public static final String EMAIL_USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Email already exists";

    public static final String OWNER = "OWNER";
    public static final String EMPLOYEE = "EMPLOYEE";


    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The user not found";



}
