package br.com.fcamara.parkingproject.utils;

import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.model.Company;

public class CompanyConvertTo {

    public CompanyDto companyDto(Company company) {
        return new CompanyDto(company);
    }
}
