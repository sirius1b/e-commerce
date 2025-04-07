package com.sirius1b.auth.controllers;


import com.sirius1b.auth.dtos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @GetMapping("/me")
    public UserInfoDto getInfo(@RequestBody TokenDto tokenDto  )  {
        // todo implement this for user info return
        // validate token here
        return null;
    }

    @PutMapping("/me")
    public void updateInfo(@RequestBody UserInfoDto userInfoDto){
        // TODO: implement,
    }
}
