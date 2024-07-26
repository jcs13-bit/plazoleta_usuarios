package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocNumber(String docNumber);


    Optional <UserEntity> getUserById(Long id);

}
