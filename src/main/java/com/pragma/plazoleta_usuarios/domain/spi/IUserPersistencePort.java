package com.pragma.plazoleta_usuarios.domain.spi;


import com.pragma.plazoleta_usuarios.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

     void saveUser(User user);

     String getUserRoleName(Long id);

     Optional<User> findByEmail(String email);
     //

     Optional<User> findByDocNumber(String docNumber);

     void encoderPassword(User user);




}

