package com.sirius1b.auth.exceptions;


import com.sirius1b.auth.utils.Exceptions;
import lombok.Getter;

@Getter
public class TokenNotFoundException extends Exception {
    private String code;

    public TokenNotFoundException(String message){
        super(message);
        code = Exceptions.USER_NOT_FOUND.toString();
    }
}
