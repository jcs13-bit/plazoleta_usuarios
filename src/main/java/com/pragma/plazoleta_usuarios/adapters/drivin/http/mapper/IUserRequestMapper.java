package com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.AddUserRequest;
import com.pragma.plazoleta_usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(target = "id", ignore = true)
    User addRequestToUser(AddUserRequest addUserRequest);









}
