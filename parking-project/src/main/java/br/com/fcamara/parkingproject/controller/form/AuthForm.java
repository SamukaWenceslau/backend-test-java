package br.com.fcamara.parkingproject.controller.form;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthForm {

    @Email
    private String email;

    @NotEmpty @NotNull
    private String password;

}
