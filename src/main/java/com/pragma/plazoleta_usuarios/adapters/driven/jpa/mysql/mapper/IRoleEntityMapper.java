package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toModel(RoleEntity roleEntity);
}
