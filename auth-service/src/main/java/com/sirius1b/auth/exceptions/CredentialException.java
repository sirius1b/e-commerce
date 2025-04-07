package com.sirius1b.auth.exceptions;


import com.sirius1b.auth.utils.Exceptions;
import lombok.Getter;

@Getter
public class CredentialException extends Exception {
    private String code;

    public CredentialException(String message){
        super(message);
        code = Exceptions.USER_NOT_FOUND.toString();
    }
}
