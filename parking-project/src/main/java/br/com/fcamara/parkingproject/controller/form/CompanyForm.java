package br.com.fcamara.parkingproject.controller.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class CompanyForm {

    //Validar dados
    @NotNull @NotEmpty @Size(min = 5)
    private String name;

    @Email
    private String email;

    @NotNull @NotEmpty @Size(min = 5)
    private String password;

    @NotNull @NotEmpty @Size(min = 5, max = 18)
    private String cnpj;

    @NotNull @NotEmpty @Size(min = 5, max = 20)
    private String tel;


}
