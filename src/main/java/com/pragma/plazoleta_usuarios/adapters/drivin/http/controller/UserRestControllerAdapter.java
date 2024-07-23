package com.pragma.plazoleta_usuarios.adapters.drivin.http.controller;


import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.AddUserRequest;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.GetRoleUserResponse;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserRequestMapper;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserResponseMapper;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    private final IUserResponseMapper userResponseMapper;



    @PostMapping("/owner")
    @Operation(summary = "Endpoint to add a new user")
    public ResponseEntity<Void> addUserOwner( @Valid @RequestBody  AddUserRequest request){
        userServicePort.saveUserOwner(userRequestMapper.addRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/getRoles")
    @Operation(summary = "Endpoint to get user role by ID")
    public ResponseEntity<GetRoleUserResponse> getUserRoles(@RequestParam("id") Long id) {

        return ResponseEntity.ok(userResponseMapper.getUserRoleNameToString(userServicePort.getUserRoleName(id)));
    }


}
