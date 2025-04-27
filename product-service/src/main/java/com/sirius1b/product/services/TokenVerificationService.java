package com.sirius1b.product.services;

import com.sirius1b.product.clients.AuthClient;
import com.sirius1b.product.exceptions.InternalException;
import com.sirius1b.product.utils.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenVerificationService {
    @Autowired
    private AuthClient authClient;

    public boolean verifyToken(String token) throws InternalException {
        log.info("Verifying token");
        if (authClient.isValid(token, "ADMIN")){
            return true;
        }

        throw new InternalException(Exceptions.INVALID_TOKEN.toString());
    }
}