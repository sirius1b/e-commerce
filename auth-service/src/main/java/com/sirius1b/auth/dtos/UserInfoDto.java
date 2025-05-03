package com.sirius1b.auth.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoDto {
    private String name;
    private String email;
    private boolean emailVerified;
    private List<String> roles;


    public static UserInfoDto from (String name, String email, boolean emailVerified, List<String> roles){
        UserInfoDto info =  new UserInfoDto();
        info.setName(name);
        info.setEmail(email);
        info.setRoles(roles);
        info.setEmailVerified(emailVerified);
        return info;
    }
}
