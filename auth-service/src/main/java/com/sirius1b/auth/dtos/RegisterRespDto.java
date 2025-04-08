package com.sirius1b.auth.dtos;


import com.sirius1b.auth.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class RegisterRespDto {
    private String email;
    private String name;
    private boolean emailVerified;

    public static RegisterRespDto from(User user){
        RegisterRespDto respDto = new RegisterRespDto();
        respDto.setEmail(user.getEmail());
        respDto.setName(user.getName());
        respDto.setEmailVerified(user.isEmailVerified());
        return respDto;
    }
}
