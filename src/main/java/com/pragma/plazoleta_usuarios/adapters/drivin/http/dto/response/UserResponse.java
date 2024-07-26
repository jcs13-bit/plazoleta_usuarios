package com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String docNumber;
    private String cellphone;
    private String email;
    private LocalDate birthDate;
    private String role;
}
