package com.pragma.plazoleta_usuarios.adapters.drivin.http.controller;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.LoginRequest;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.AuthResponse;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IAuthMapper;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserRequestMapper;
import com.pragma.plazoleta_usuarios.domain.api.IAuthServicePort;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.model.Auth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final IAuthMapper authMapper;
    private final IAuthServicePort authServicePort;




    @Operation(summary = "Login endpoint")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Auth auth = authMapper.toAuth(loginRequest);

        AuthResponse token = authMapper.toAuthResponse(authServicePort.login(auth));
        return ResponseEntity.ok(token);
    }


}
