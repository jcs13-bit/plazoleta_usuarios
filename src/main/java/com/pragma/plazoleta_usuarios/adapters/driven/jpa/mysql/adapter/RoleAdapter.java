package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleAlreadyExistsException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleAdapter implements IRolesPersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(Role role) {
        String normalizedName = role.getName().trim().toUpperCase();

//        if (roleRepository.findByName(normalizedName).isPresent()) {
//            throw new RoleAlreadyExistsException();
//        }

        role.setName(normalizedName);
        roleRepository.save(roleEntityMapper.toEntity(role));

    }

    @Override
    public String getRoleNameById(Long id) {
        Optional<RoleEntity> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleOptional.get().getName();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleEntityMapper.toModel(roleRepository.findByName(name));


    }
}
