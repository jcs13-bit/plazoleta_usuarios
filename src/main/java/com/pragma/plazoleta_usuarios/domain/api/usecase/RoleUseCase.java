package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.domain.api.IRoleServicePort;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;

public class RoleUseCase implements IRoleServicePort {
    public RoleUseCase(IRolesPersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    private IRolesPersistencePort rolePersistencePort;

    @Override
    public void saveRole(Role role) {
        rolePersistencePort.saveRole(role);
    }

    @Override
    public String getRoleNameById(Long id) {
        return rolePersistencePort.getRoleNameById(id);
    }
}
