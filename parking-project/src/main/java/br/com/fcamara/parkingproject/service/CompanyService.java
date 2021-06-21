package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.model.Company;

import br.com.fcamara.parkingproject.repository.CompanyRepository;
import br.com.fcamara.parkingproject.utils.ConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompanyService extends ConvertTo {

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<CompanyDto> show(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if(company.isPresent()){
            return ResponseEntity.ok(companyDto(company.get()));
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<CompanyDto> create(CompanyForm form) {
        Boolean existCnpj = companyRepository.existsByCnpj(form.getCnpj());

        // Verificar se cnpj, já não está cadastrado
        if (!existCnpj) {

            Company company = company(form);

            Company save = companyRepository.save(company);

            return ResponseEntity.ok(companyDto(save));
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<CompanyDto> update(long id, CompanyForm form) {
        Boolean optional = companyRepository.existsById(id);

        if (optional) {
            Company company = companyRepository.getById(id);

            company.setCnpj(form.getCnpj());
            company.setName(form.getName());
            company.setTel(form.getTel());

            return ResponseEntity.ok(companyDto(company));

        }

        return ResponseEntity.badRequest().build();
    }
}
