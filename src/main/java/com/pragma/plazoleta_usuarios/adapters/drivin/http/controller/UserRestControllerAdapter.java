package com.pragma.plazoleta_usuarios.adapters.drivin.http.controller;


import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.AddUserRequest;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserRequestMapper;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @PostMapping("/")
    @Operation(summary = "Endpoint to add a new user")
    public ResponseEntity<Void> addUser( @Valid @RequestBody  AddUserRequest request){
        userServicePort.saveUser(userRequestMapper.addRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
