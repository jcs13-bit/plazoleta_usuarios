package com.pragma.plazoleta_usuarios.domain.api;

import com.pragma.plazoleta_usuarios.domain.model.User;

public interface IUserServicePort {
  void saveUser(User user);
}
