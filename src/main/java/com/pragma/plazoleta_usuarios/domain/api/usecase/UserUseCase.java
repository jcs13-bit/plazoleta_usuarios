package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserAlreadyExistsException;
import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.exceptions.BirthDateException;
import com.pragma.plazoleta_usuarios.domain.exceptions.RoleNotFoundExceptionDomain;
import com.pragma.plazoleta_usuarios.domain.model.Role;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IRolesPersistencePort;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta_usuarios.domain.util.Constants;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {

    private IUserPersistencePort userPersistencePort;
    private IRolesPersistencePort rolesPersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort, IRolesPersistencePort rolesPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolesPersistencePort = rolesPersistencePort;
    }

    @Override
    public void saveUser(User user) {


            LocalDate birthDate = user.getBirthDate();

            LocalDate currentDate = LocalDate.now();


            int age = Period.between(birthDate, currentDate).getYears();

            if (age >= 18){
                userPersistencePort.saveUser(user);
            }else {
                throw new BirthDateException();
            }

    }

    @Override
    public void saveUserOwner(User user) {

        validateUser(user);
        //colocar el "OWNER" en una constante

        user.setRole(getRoleByName("OWNER"));
        userPersistencePort.saveUser(user);



    }
    private void validateUser(User user) {
        validateBirthDate(user.getBirthDate());
        validateEmail(user.getEmail());
        validateDocNumber(user.getDocNumber());

    }
    private  void validateEmail(String email){
        if (userPersistencePort.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
    }

    private  void validateDocNumber(String docNumber){
        if (userPersistencePort.findByDocNumber(docNumber).isPresent()){
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
    }

    private  void validateBirthDate(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18){
            throw new BirthDateException();
        }
    }
    private Role getRoleByName(String name){
       Role role = rolesPersistencePort.getRoleByName(name);
       if (role == null){

           //poner la excepcion correspondiente del rol no encontrado
           throw new RoleNotFoundExceptionDomain(Constants.ROLE_NOT_FOUND_EXCEPTION_MESSAGE);
       }
       return role;
    }

    @Override
    public String getUserRoleName(Long id) {
        return userPersistencePort.getUserRoleName(id);
    }




}
