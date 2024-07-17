package com.pragma.plazoleta_usuarios.domain.spi;


import com.pragma.plazoleta_usuarios.domain.model.User;

public interface IUserPersistencePort {

     void saveUser(User user);

}

