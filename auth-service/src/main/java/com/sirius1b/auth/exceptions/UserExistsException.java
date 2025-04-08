package com.sirius1b.auth.exceptions;

import com.sirius1b.auth.utils.Exceptions;
import lombok.Getter;

@Getter
public class UserExistsException extends Exception{

    private String code;

    public UserExistsException(String message){
        super(message);
        code = Exceptions.DUPLICATE_USER.toString();
    }
}


