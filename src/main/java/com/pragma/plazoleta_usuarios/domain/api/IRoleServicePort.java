package com.pragma.plazoleta_usuarios.domain.api;

import com.pragma.plazoleta_usuarios.domain.model.Role;

public interface IRoleServicePort {
    void saveRole(Role role);
    String getRoleNameById(Long id);
}
