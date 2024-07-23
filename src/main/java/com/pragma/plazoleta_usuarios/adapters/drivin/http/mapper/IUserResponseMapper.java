package com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.GetRoleUserResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    GetRoleUserResponse getUserRoleNameToString(String roleName);

}
