package com.sirius1b.product.exceptions;

import com.sirius1b.product.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFound(InternalException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), e.getCode());
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
