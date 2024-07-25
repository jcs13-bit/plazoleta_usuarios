package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.domain.api.IAuthServicePort;
import com.pragma.plazoleta_usuarios.domain.exceptions.ConstantsDomain;
import com.pragma.plazoleta_usuarios.domain.exceptions.InvalidPasswordException;
import com.pragma.plazoleta_usuarios.domain.model.Auth;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IAuthPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.ITokenPersistencePort;

public class AuthUseCase implements IAuthServicePort {

    private final ITokenPersistencePort tokenPersistencePort;
    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(ITokenPersistencePort tokenPersistencePort, IAuthPersistencePort authPersistencePort) {
        this.tokenPersistencePort = tokenPersistencePort;
        this.authPersistencePort = authPersistencePort;
    }
    @Override
    public String login(Auth auth) {
        User user = authPersistencePort.findByEmail(auth.getEmail());
        if (!authPersistencePort.checkPassword(auth.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException(ConstantsDomain.INVALID_PASSWORD_EXCEPTION_MESSAGE);
        }
        authPersistencePort.login(auth);
        return tokenPersistencePort.getToken(user);


    }



}
