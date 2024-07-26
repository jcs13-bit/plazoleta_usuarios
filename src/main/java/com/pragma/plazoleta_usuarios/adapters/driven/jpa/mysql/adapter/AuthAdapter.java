package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.plazoleta_usuarios.domain.exceptions.ConstantsDomain;
import com.pragma.plazoleta_usuarios.domain.model.Auth;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IAuthPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public void login(Auth auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
    }
    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ConstantsDomain.USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE + email));
        return userEntityMapper.toModel(userEntity);
    }
    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
