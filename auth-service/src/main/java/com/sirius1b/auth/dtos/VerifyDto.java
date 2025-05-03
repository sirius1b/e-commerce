package com.sirius1b.auth.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerifyDto {

    private String token;
    private List<String> roles;

    public static VerifyDto from(String token, List<String> roles){

        VerifyDto dto = new VerifyDto();
        dto.token = token;
        dto.roles = new ArrayList<>(roles);
        return dto;
    }
}
