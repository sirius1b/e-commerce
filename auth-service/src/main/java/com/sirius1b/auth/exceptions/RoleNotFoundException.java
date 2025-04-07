package com.sirius1b.auth.exceptions;


import com.sirius1b.auth.utils.Exceptions;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends Exception {
    private String code;

    public RoleNotFoundException(String message){
        super(message);
        code = Exceptions.ROLE_NOT_FOUND.toString();
    }
}
