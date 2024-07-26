package com.pragma.plazoleta_usuarios.configuration;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The Field indicated is Empty or Null";

    public static final String FIELD_BIRTH_DATE_IS_VALID_MESSAGE = "Field 'Birth Date' does not valid, must be of legal age";

    public static final String FIELD_BIRTH_DATE_IS_NULL_MESSAGE = "Field 'Birth Date' is Empty or Null";

    public static final String MAX_CHAR_EXCEPTION_MESSAGE = "The field exceeds the maximum character limit specified";

    public static final String FIELD_CELLPHONE_PATTERN_EXCEPTION_MESSAGE = "the cell phone field only allows the + and 13 numbers";

    public static final String FIELD_DOC_NUMBER_PATTERN_EXCEPTION_MESSAGE = "Field 'Doc Number' Not is a valid Number";

    public static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "The document number must be numerical";



    public static final String ROLE_NOT_FOUND_EXCEPTION_MESSAGE = "The role you want to use does not exist";

    public static final String ROLE_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The role you want to create already exists";

    public static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user you want to create already exists";

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The user not found";

    public static final String USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE = "User not found with email";

    public static final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "Invalid Password";






}
