package com.pragma.plazoleta_usuarios.adapters.drivin.http.mapper;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request.AddRoleRequest;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface IRoleRequestMapper {
    @Mapping(target = "id", ignore = true)
    Role addRequestToRole(AddRoleRequest addRoleRequest);
}
