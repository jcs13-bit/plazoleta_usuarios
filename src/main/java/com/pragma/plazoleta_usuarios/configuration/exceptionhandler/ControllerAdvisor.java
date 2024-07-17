package com.pragma.plazoleta_usuarios.configuration.exceptionhandler;

import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleAlreadyExistsException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.RoleNotFoundException;
import com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.exception.UserAlreadyExistsException;
import com.pragma.plazoleta_usuarios.configuration.Constants;
import com.pragma.plazoleta_usuarios.domain.exceptions.BirthDateException;
import com.pragma.plazoleta_usuarios.domain.exceptions.BirthDateIsNullException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    public String getErrorMessage(FieldError error) {
        if (error == null) {
            return "Validation error";
        }
        String fieldName = error.getField();
        String code = error.getCode();
        if (code != null && code.equals("NotBlank")) {
            return String.format("%s: %s", fieldName, Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        } else if (code != null && code.equals("Size")) {
            return String.format("%s: %s", fieldName, Constants.MAX_CHAR_EXCEPTION_MESSAGE);
        } else if (code != null && code.equals("Pattern")) {
            if (fieldName.equals("docNumber")) {
                return String.format("%s: %s", fieldName, Constants.FIELD_DOC_NUMBER_PATTERN_EXCEPTION_MESSAGE);
            } else if (fieldName.equals("cellphone")) {
                return String.format("%s: %s", fieldName, Constants.FIELD_CELLPHONE_PATTERN_EXCEPTION_MESSAGE);
            }
        } else if (code != null && code.equals("NotNull")) {
            return String.format("%s: %s", fieldName, Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        } else {
            return error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        if (result.hasFieldErrors()) {
            FieldError error = result.getFieldError();

            if (error != null) {
                String errorMessage = getErrorMessage(error);

                return ResponseEntity.badRequest().body(new ExceptionResponse(
                        errorMessage, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(BirthDateException.class)
    public ResponseEntity<ExceptionResponse> handleBirthDateExceptions(BirthDateException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                    String.format(Constants.FIELD_BIRTH_DATE_IS_VALID_MESSAGE, ex.getMessage()),
                    HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));

    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleRoleAlreadyExistsException(RoleAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                String.format(Constants.ROLE_ALREADY_EXISTS_EXCEPTION_MESSAGE, ex.getMessage()),
                HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(BirthDateIsNullException.class)
    public ResponseEntity<ExceptionResponse> handleBirthDateIsNullExceptions(BirthDateIsNullException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                String.format(Constants.FIELD_BIRTH_DATE_IS_NULL_MESSAGE, ex.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));

    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                String.format(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE, ex.getMessage()),
                HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ROLE_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

}
