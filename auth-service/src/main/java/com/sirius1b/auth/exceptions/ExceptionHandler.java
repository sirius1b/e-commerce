package com.sirius1b.auth.exceptions;

import com.sirius1b.auth.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(UserNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), e.getCode());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(TokenNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), e.getCode());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CredentialException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(CredentialException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), e.getCode());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateUser(UserExistsException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), e.getCode());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
