package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.controller.dto.DetailCompanyDto;
import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.model.Company;

import br.com.fcamara.parkingproject.repository.CompanyRepository;
import br.com.fcamara.parkingproject.utils.CompanyConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Utils
    private final CompanyConvertTo companyConvertTo;

    public CompanyService() {
        this.companyConvertTo = new CompanyConvertTo();
    }


    public ResponseEntity<DetailCompanyDto> index(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if(company.isPresent()){
            return ResponseEntity.ok(new DetailCompanyDto(company.get()));
        }

        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<CompanyDto> show(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if(company.isPresent()){
            return ResponseEntity.ok(companyConvertTo.companyDto(company.get()));
        }

        return ResponseEntity.notFound().build();

    }


    public ResponseEntity<CompanyDto> create(CompanyForm form) {
        Optional<Company> cnpj = companyRepository.findByCnpj(form.getCnpj());

        // Verificar se cnpj, já não está cadastrado
        if (!cnpj.isPresent()) {

            Company company = new Company(form.getName(), form.getCnpj(), form.getTel());

            Company save = companyRepository.save(company);

            return ResponseEntity.ok(companyConvertTo.companyDto(save));
        }

        return ResponseEntity.badRequest().build();
    }


    public ResponseEntity<CompanyDto> update(long id, CompanyForm form) {
        Optional<Company> optional = companyRepository.findById(id);

        if (optional.isPresent()) {
            Company company = companyRepository.getById(id);

            company.setCnpj(form.getCnpj());
            company.setName(form.getName());
            company.setTel(form.getTel());

            return ResponseEntity.ok(companyConvertTo.companyDto(company));

        }

        return ResponseEntity.badRequest().build();
    }


}
