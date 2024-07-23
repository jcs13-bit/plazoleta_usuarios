package com.pragma.plazoleta_usuarios.domain.api;

import com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.response.GetRoleUserResponse;
import com.pragma.plazoleta_usuarios.domain.model.User;

public interface IUserServicePort {
  void saveUser(User user);

  void saveUserOwner(User user);


  String getUserRoleName(Long id);
}
