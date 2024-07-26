package com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.LoginRequest;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.AuthResponse;
import com.pragma.plazoleta_usuarios.domain.model.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthMapper {
    Auth toAuth(LoginRequest request);

    AuthResponse toAuthResponse(String token);

}
