package com.pragma.plazoleta_usuarios.domain.spi;

import com.pragma.plazoleta_usuarios.domain.model.Role;
public interface IRolesPersistencePort {
    void saveRole(Role role);
    String getRoleNameById(Long id);

    Role getRoleByName(String name);
}
