package com.pragma.plazoleta_usuarios.adapters.drivin.http.controller;


import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.AddUserRequest;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.GetRoleUserResponse;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.UserResponse;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserRequestMapper;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper.IUserResponseMapper;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping(value = "/getRoles")
    @Operation(summary = "Endpoint to get user role by ID")
    public ResponseEntity<GetRoleUserResponse> getUserRoles(@RequestParam("id") Long id) {

        return ResponseEntity.ok(userResponseMapper.getUserRoleNameToString(userServicePort.getUserRoleName(id)));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/owner")
    @Operation(summary = "Endpoint to add a new user")
    public ResponseEntity<Void> addUserOwner( @Valid @RequestBody  AddUserRequest request){
        userServicePort.saveUserOwner(userRequestMapper.addRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/employee")
    @Operation(summary = "Endpoint to add a new user")
    public Long addUserEmployee( @Valid @RequestBody  AddUserRequest request){
        return userServicePort.saveUserEmployee(userRequestMapper.addRequestToUser(request));
    }

    @PostMapping("/client")
    @Operation(summary = "Endpoint to add a new user")
    public ResponseEntity<Void> addUserClient( @Valid @RequestBody  AddUserRequest request){
        userServicePort.saveUserClient(userRequestMapper.addRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    

    @GetMapping("/")
    public UserResponse getUserById(@RequestParam("id") Long id) {
        User user = userServicePort.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userResponseMapper.userToUserResponse(user);
    }














}
