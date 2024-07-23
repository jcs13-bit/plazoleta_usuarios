package com.pragma.plazoleta_usuarios.domain.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The Field indicated is Empty or Null";

    public static final String FIELD_BIRTH_DATE_IS_VALID_MESSAGE = "Field 'Birth Date' does not valid, must be of legal age";

    public static final String ROLE_NOT_FOUND_EXCEPTION_MESSAGE = "The role you want to use does not exist";
    public  static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user already exists";
}
