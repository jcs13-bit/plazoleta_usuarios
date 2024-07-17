package com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request;


import com.pragma.plazoleta_usuarios.adapters.drivin.http.exceptions.DtoConstants;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddUserRequest {
    @NotBlank(message = DtoConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 25, message = DtoConstants.FIELD_NAME_SIZE_MESSAGE)
    private String name;

    @NotBlank(message = DtoConstants.FIELD_LAST_NAME_NULL_MESSAGE)
    @Size(max = 25, message = DtoConstants.FIELD_LAST_NAME_SIZE_MESSAGE)
    private String lastName;
    @NotBlank(message = DtoConstants.FIELD_DOC_NUMBER_NULL_MESSAGE)
    @Pattern(regexp = "\\d+", message = DtoConstants.FIELD_DOC_NUMBER_IS_VALID_MESSAGE)
    @Size(max = 25, message = DtoConstants.FIELD_DOC_NUMBER_SIZE_MESSAGE)
    private String docNumber;

    @NotBlank(message = DtoConstants.FIELD_CELLPHONE_NULL_MESSAGE)
    @Size(max = 13, message = DtoConstants.FIELD_CELLPHONE_SIZE_MESSAGE)
    @Pattern(regexp = "\\+?\\d{1,13}", message = DtoConstants.FIELD_CELLPHONE_IS_VALID_MESSAGE)
    private String cellphone;


    @NotNull(message = DtoConstants.FIELD_BIRTH_DATE_NULL_MESSAGE)
    private LocalDate birthDate;



    @NotBlank(message = DtoConstants.FIELD_EMAIL_NULL_MESSAGE)
    @Email(message = DtoConstants.NOT_IS_A_EMAIL)
    private String email;

    @NotBlank(message = DtoConstants.FIELD_PASSWORD_NULL_MESSAGE)
    private String password;

    @NotNull(message = DtoConstants.FIELD_ROL_NULL_MESSAGE)
    private Role role;






}
