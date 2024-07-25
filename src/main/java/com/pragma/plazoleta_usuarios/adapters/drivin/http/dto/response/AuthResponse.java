package com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private final String token;
}
