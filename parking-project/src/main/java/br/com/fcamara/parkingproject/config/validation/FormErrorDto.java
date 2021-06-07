package br.com.fcamara.parkingproject.config.validation;

import lombok.Getter;

@Getter
public class FormErrorDto {

    private String field;
    private String err;

    public FormErrorDto(String field, String err) {
        this.field = field;
        this.err = err;
    }
}
