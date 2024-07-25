package com.pragma.plazoleta_usuarios.domain.spi;

import com.pragma.plazoleta_usuarios.domain.model.Auth;
import com.pragma.plazoleta_usuarios.domain.model.User;

public interface IAuthPersistencePort {

    User findByEmail(String email);
    void login(Auth auth);
    boolean checkPassword(String password, String hashedPassword);
}
