package com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.GetRoleUserResponse;
import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.UserResponse;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    GetRoleUserResponse getUserRoleNameToString(String roleName);

    default String mapRoleToString(Role role) {
        return role.getName();
    }

    UserResponse userToUserResponse(User user);


}
