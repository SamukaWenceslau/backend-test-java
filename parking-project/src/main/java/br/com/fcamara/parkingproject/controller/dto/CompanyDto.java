package br.com.fcamara.parkingproject.controller.dto;

import br.com.fcamara.parkingproject.model.Company;
import lombok.Getter;

@Getter
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String cnpj;
    private String tel;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.email = company.getEmail();
        this.cnpj = company.getCnpj();
        this.tel = company.getTel();
    }
}
