package com.pragma.plazoleta_usuarios.configuration;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.plazoleta_usuarios.domain.api.IRoleServicePort;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.api.usecase.RoleUseCase;
import com.pragma.plazoleta_usuarios.domain.api.usecase.UserUseCase;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final IUserRepository userRepository;

    private final IUserEntityMapper userEntityMapper;

    @Bean
    public IRolesPersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository,roleRepository, userEntityMapper);
    }
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }



}
