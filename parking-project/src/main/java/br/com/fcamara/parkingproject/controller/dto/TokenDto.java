package br.com.fcamara.parkingproject.controller.dto;

import lombok.Getter;

@Getter
public class TokenDto {

    private String type;
    private String token;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
