package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserAlreadyExistsException;
import com.pragma.plazoleta_usuarios.domain.exceptions.BirthDateException;
import com.pragma.plazoleta_usuarios.domain.exceptions.ConstantsDomain;
import com.pragma.plazoleta_usuarios.domain.exceptions.UserIdNotFoundException;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta_usuarios.domain.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolesPersistencePort rolesPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUserOwner_shouldSaveUserWithOwnerRole() {
        // Arrange
        User user = new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "owner", null);
        Role ownerRole = new Role(1L, ConstantsDomain.OWNER, "OWNER");
        when(rolesPersistencePort.getRoleByName(ConstantsDomain.OWNER)).thenReturn(ownerRole);
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocNumber(user.getDocNumber())).thenReturn(Optional.empty());

        // Act
        userUseCase.saveUserOwner(user);

        // Assert
        verify(userPersistencePort, times(1)).saveUser(user);
        assertEquals(ConstantsDomain.OWNER, user.getRole().getName());
    }

    @Test
    void saveUserClient_shouldSaveUserWithClientRole() {
        // Arrange
        User user = new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "client", null);
        Role clientRole = new Role(2L, ConstantsDomain.CLIENT, "CLIENT");
        when(rolesPersistencePort.getRoleByName(ConstantsDomain.CLIENT)).thenReturn(clientRole);
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocNumber(user.getDocNumber())).thenReturn(Optional.empty());

        // Act
        userUseCase.saveUserClient(user);

        // Assert
        verify(userPersistencePort, times(1)).saveUser(user);
        assertEquals(ConstantsDomain.CLIENT, user.getRole().getName());
    }

    @Test
    void saveUserEmployee_shouldSaveUserWithEmployeeRole() {
        // Arrange
        User user = new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "employee", null);
        Role employeeRole = new Role(3L, ConstantsDomain.EMPLOYEE, "EMPLOYEE");
        when(rolesPersistencePort.getRoleByName(ConstantsDomain.EMPLOYEE)).thenReturn(employeeRole);
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocNumber(user.getDocNumber())).thenReturn(Optional.empty());

        // Act
        Long result = userUseCase.saveUserEmployee(user);

        // Assert
        verify(userPersistencePort, times(1)).saveUserEmployee(user);
        assertEquals(ConstantsDomain.EMPLOYEE, user.getRole().getName());
        assertNotNull(result);
    }

    @Test
    void getUserRoleName_shouldReturnUserRoleName() {
        // Arrange
        Long userId = 1L;
        String roleName = ConstantsDomain.OWNER;
        when(userPersistencePort.getUserRoleName(userId)).thenReturn(roleName);

        // Act
        String result = userUseCase.getUserRoleName(userId);

        // Assert
        verify(userPersistencePort, times(1)).getUserRoleName(userId);
        assertEquals(roleName, result);
    }

    @Test
    void getUserById_shouldReturnUser() {
        // Arrange
        Long userId = 1L;
        User user = new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "owner", new Role(1L, "EMPLOYEE", "EMPLOYEE"));
        when(userPersistencePort.getUserById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userUseCase.getUserById(userId);

        // Assert
        verify(userPersistencePort, times(1)).getUserById(userId);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserById_shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userPersistencePort.getUserById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        UserIdNotFoundException exception = assertThrows(UserIdNotFoundException.class, () -> userUseCase.getUserById(userId));
        assertEquals(ConstantsDomain.USER_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validateEmail_shouldThrowExceptionWhenEmailExists() {
        // Arrange
        String email = "jose@gmail.com";
        when(userPersistencePort.findByEmail(email)).thenReturn(Optional.of(new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "client", null)));

        // Act & Assert
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.saveUserOwner(new User(null, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), email, "owner", null));
        });
        assertEquals(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validateDocNumber_shouldThrowExceptionWhenDocNumberExists() {
        // Arrange
        String docNumber = "10909";
        when(userPersistencePort.findByDocNumber(docNumber)).thenReturn(Optional.of(new User(1L, "jose", "123", "10909", "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "client", null)));

        // Act & Assert
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.saveUserOwner(new User(null, "jose", "123", docNumber, "+573163718182", LocalDate.of(2000, 1, 1), "jose@gmail.com", "owner", null));
        });
        assertEquals(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validateBirthDate_shouldThrowExceptionWhenUserIsUnderage() {
        // Arrange
        LocalDate birthDate = LocalDate.now().minusYears(17);

        // Act & Assert
        BirthDateException exception = assertThrows(BirthDateException.class, () -> {
            userUseCase.saveUserOwner(new User(null, "jose", "123", "10909", "+573163718182", birthDate, "jose@gmail.com", "owner", null));
        });
        assertNotNull(exception);
    }
}