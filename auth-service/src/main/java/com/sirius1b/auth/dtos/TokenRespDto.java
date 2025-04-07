package com.sirius1b.auth.dtos;

import com.sirius1b.auth.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TokenRespDto {

    private String token;
    private Date expiryAt;

    public static TokenRespDto from(Token token){
        TokenRespDto tokenRespDto = new TokenRespDto();
        tokenRespDto.setToken(token.getValue());
        tokenRespDto.setExpiryAt(new Date(token.getExpiryAt()));
        return tokenRespDto;
    }
}
