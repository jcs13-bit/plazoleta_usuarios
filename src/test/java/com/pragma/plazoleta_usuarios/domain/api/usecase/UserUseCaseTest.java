package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = user = new User(1l,"user1","lastName1","1007","+52154263", LocalDate.of(2001, 1, 1),"email@gmail.com","password1",new Role(1L, "Test", "test role"));

        userUseCase.saveUser(user);

        verify(userPersistencePort, times(1)).saveUser(user);
    }

}