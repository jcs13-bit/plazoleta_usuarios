package com.pragma.plazoleta_usuarios.domain.api;

import com.pragma.plazoleta_usuarios.domain.model.Auth;

public interface IAuthServicePort {

    String login(Auth auth);
}
