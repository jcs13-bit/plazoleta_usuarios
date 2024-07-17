package com.pragma.plazoleta_usuarios.adapters.drivin.http.exceptions;

public class DtoConstants {
    private DtoConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";

    public static final String FIELD_NAME_SIZE_MESSAGE = "Field 'Name' cannot be longer than 25 characters";


    public static final String FIELD_LAST_NAME_NULL_MESSAGE = "Field 'Last Name' cannot be null";

    public static final String FIELD_LAST_NAME_SIZE_MESSAGE = "Field 'Last Name' cannot be longer than 25 characters";

    public static final String FIELD_DOC_NUMBER_NULL_MESSAGE = "Field 'Doc Number' cannot be null";

    public static final String FIELD_DOC_NUMBER_SIZE_MESSAGE = "Field 'Doc Number' cannot be longer than 25 characters";


    public static final String FIELD_CELLPHONE_NULL_MESSAGE = "Field 'Cellphone' cannot be null";

    public static final String FIELD_CELLPHONE_SIZE_MESSAGE = "Field 'Cellphone' cannot be longer than 13 characters";

    public static final String FIELD_CELLPHONE_IS_VALID_MESSAGE = "Field 'Cellphone' Not is a valid Cellphone";

    public static final String FIELD_DOC_NUMBER_IS_VALID_MESSAGE = "Field 'Doc Number' Not is a valid Doc Number";



    public static final String FIELD_BIRTH_DATE_NULL_MESSAGE = "Field 'Birth Date' cannot be null";


    public static final String FIELD_EMAIL_NULL_MESSAGE = "Field 'Email' cannot be null";

    public static final String FIELD_BIRTH_DATE_IS_VALID_MESSAGE = "Field 'Birth Date' does not valid, must be of legal age";
    public static final String NOT_IS_A_EMAIL = "Not is a valid Email";

    public static final String FIELD_PASSWORD_NULL_MESSAGE = "Field 'Password' cannot be null";

    public static final String FIELD_ROL_NULL_MESSAGE = "Field 'Rol' cannot be null";




}
