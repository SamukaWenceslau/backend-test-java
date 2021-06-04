package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.model.Company;

import br.com.fcamara.parkingproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company create(CompanyForm form) {
        Company company = new Company(form.getName(), form.getCnpj(), form.getTel());

        return companyRepository.save(company);
    }
}
