package com.sirius1b.auth.dtos;

import com.sirius1b.auth.models.Token;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
