package com.pragma.plazoleta_usuarios.domain.spi;

import com.pragma.plazoleta_usuarios.domain.model.User;

public interface ITokenPersistencePort {

    String getToken(User user);

}
