package com.sirius1b.product.exceptions;

import lombok.Getter;

public class InternalException extends Exception {
    @Getter
    private String code;

    public InternalException(String message){
        super(message);
        code = message;
    }
}
