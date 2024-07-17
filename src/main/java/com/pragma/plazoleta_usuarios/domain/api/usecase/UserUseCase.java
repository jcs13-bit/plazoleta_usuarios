package com.pragma.plazoleta_usuarios.domain.api.usecase;

import com.pragma.plazoleta_usuarios.domain.api.IUserServicePort;
import com.pragma.plazoleta_usuarios.domain.exceptions.BirthDateException;
import com.pragma.plazoleta_usuarios.domain.model.User;
import com.pragma.plazoleta_usuarios.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {

    private IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
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


}
