package com.sirius1b.auth.controllers;


import com.sirius1b.auth.dtos.*;
import com.sirius1b.auth.exceptions.UserNotFoundException;
import com.sirius1b.auth.services.JwtService;
import com.sirius1b.auth.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/me")
    public UserInfoDto getInfo(@RequestHeader("Authorization") String token ) throws UserNotFoundException {
        token = token.substring(7);
        String email = jwtService.extractUsername(token);
        return userService.getUser(email);
    }

    @PutMapping("/me")
    public void updateInfo(@RequestHeader("Authorization") String token, @RequestBody UserInfoDto userInfoDto) throws UserNotFoundException {
        token = token.substring(7);
        String email = jwtService.extractUsername(token);
        userService.updateUser(email, userInfoDto.getName(), userInfoDto.getEmail());
    }
}
