package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.plazoleta_usuarios.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    User toModel(UserEntity userEntity);
}
