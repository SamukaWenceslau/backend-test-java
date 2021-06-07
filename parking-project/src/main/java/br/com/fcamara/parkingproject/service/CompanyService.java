package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.model.Company;

import br.com.fcamara.parkingproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company create(CompanyForm form) {
        Optional<Company> cnpj = companyRepository.findByCnpj(form.getCnpj());

        // Verificar se cnpj, já não está cadastrado
        if (!cnpj.isPresent()) {
            Company company = new Company(form.getName(), form.getCnpj(), form.getTel());
            return companyRepository.save(company);
        }

        return null;
    }


    public Company update(long id, CompanyForm form) {
        Optional<Company> optional = companyRepository.findById(id);

        if (optional.isPresent()) {
            Company company = companyRepository.getById(id);

            company.setCnpj(form.getCnpj());
            company.setName(form.getName());
            company.setTel(form.getTel());

            return company;

        }

        return null;


    }
}
