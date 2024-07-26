package com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.exceptions.DtoConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = DtoConstants.FIELD_EMAIL_NULL_MESSAGE)
    @Email
    private String email;

    @NotBlank(message = DtoConstants.FIELD_PASSWORD_NULL_MESSAGE)
    private String password;
}
