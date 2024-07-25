package com.pragma.plazoleta_usuarios.configuration;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter.AuthAdapter;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.plazoleta_usuarios.configuration.jwt.JwtService;
import com.pragma.plazoleta_usuarios.domain.api.IAuthServicePort;
import com.pragma.plazoleta_usuarios.domain.api.IRoleServicePort;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.api.usecase.AuthUseCase;
import com.pragma.plazoleta_usuarios.domain.api.usecase.RoleUseCase;
import com.pragma.plazoleta_usuarios.domain.api.usecase.UserUseCase;
import com.pragma.plazoleta_usuarios.domain.exceptions.ConstantsDomain;
import com.pragma.plazoleta_usuarios.domain.exceptions.EmailAlreadyExistsException;
import com.pragma.plazoleta_usuarios.domain.spi.IAuthPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.ITokenPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final IUserRepository userRepository;

    private final IUserEntityMapper userEntityMapper;

    @Bean
    public IRolesPersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(PasswordEncoder passwordEncoder) {
        return new UserAdapter(userRepository,roleRepository, userEntityMapper, passwordEncoder());
    }
    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IRolesPersistencePort rolesPersistencePort) {
        return new UserUseCase(userPersistencePort, rolesPersistencePort );

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ITokenPersistencePort tokenPort() {
        return new JwtService();
    }

    @Bean
    public IAuthPersistencePort authPersistencePort(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        return new AuthAdapter(userRepository, userEntityMapper, passwordEncoder, authenticationManager);
    }

    @Bean
    public IAuthServicePort authServicePort(IAuthPersistencePort authPersistencePort) {
        return new AuthUseCase(tokenPort(), authPersistencePort);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailService() {
        return email -> (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistsException(ConstantsDomain.EMAIL_USER_ALREADY_EXISTS_EXCEPTION_MESSAGE));
    }

}
