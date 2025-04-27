package com.sirius1b.auth.controllers;


import com.sirius1b.auth.dtos.*;
import com.sirius1b.auth.exceptions.*;
import com.sirius1b.auth.models.Token;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterRespDto signup(@RequestBody RegisterDto request) throws RoleNotFoundException, UserExistsException {
        log.info(request.toString());
        User user =  userService.register( request.getName(),
                                    request.getEmail(),
                                    request.getPassword());

        return RegisterRespDto.from(user);
    }

    @PostMapping("/login")
    public TokenRespDto login(@RequestBody LoginDto request) throws UserNotFoundException, CredentialException {
        log.info(request.toString());
        Token token = userService.login(request.getEmail(), request.getPassword());
        return TokenRespDto.from(token);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody TokenDto req ) throws TokenNotFoundException {
        log.info(req.toString());
        userService.logout(req.getToken());
    }


    @PostMapping("/verify-token")
    public Boolean verify(@RequestHeader("Authorization") String token, @RequestHeader("X-ROLE") String role) throws TokenNotFoundException {
        role = role == null || role.isEmpty() ? "USER": role;
        return verifyToken(token, role) ;
    }

    private Boolean verifyToken(String token, String role) {
        token = token.substring(7);
        List<String> roles = userService.extractRoles(token);
        return roles.contains(role);
    }


}
