package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserAlreadyExistsException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {


    private  final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {



        String normalizedEmail = user.getEmail().trim().toUpperCase();

        String docNumber = user.getDocNumber();


        if (userRepository.findByEmail(normalizedEmail).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.findByDocNumber(docNumber).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (roleRepository.findById(user.getRole().getId()).isEmpty()) {
            throw new RoleNotFoundException();
        }

        user.setEmail(normalizedEmail);

        userRepository.save(userEntityMapper.toEntity(user));


    }
}
