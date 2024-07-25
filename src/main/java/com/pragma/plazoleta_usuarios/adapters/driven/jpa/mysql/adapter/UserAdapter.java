package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserAlreadyExistsException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {


    private  final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(User user) {

        userRepository.save(userEntityMapper.toEntity(user));

    }
    //SOLO DEBE LLAMAR AL REPOSITORIO, NO DEBE CREAR LOGICA DE NEGOCIO

    @Override
    public String getUserRoleName(Long id) {


         UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());;


         if(userEntity.getRole() == null){
             throw new RoleNotFoundException();
         }


         Long roleId = userEntity.getRole().getId();

         return roleRepository.findById(roleId).get().getName();

    }

    @Override
    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email).map(userEntityMapper::toModel);



    }

    @Override
    public Optional<User> findByDocNumber(String docNumber) {

        return userRepository.findByDocNumber(docNumber).map(userEntityMapper::toModel);
    }
    @Override
    public void encoderPassword(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
