package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleUseCaseTest {

    @Mock
    private IRolesPersistencePort rolePersistencePort;
    @InjectMocks
    private RoleUseCase roleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRole() {
        Role role = new Role(1L, "test","test");

        roleUseCase.saveRole(role);

        verify(rolePersistencePort, times(1)).saveRole(role);
    }

    @Test
    void testGetRoleNameById() {
        Long roleId = 1L;
        String expectedRoleName = "test";

        when(rolePersistencePort.getRoleNameById(roleId)).thenReturn(expectedRoleName);

        String actualRoleName = roleUseCase.getRoleNameById(roleId);

        assertEquals(expectedRoleName, actualRoleName);
    }

}