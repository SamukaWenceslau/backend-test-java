package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.controller.dto.DetailCompanyDto;
import br.com.fcamara.parkingproject.model.Company;
import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> list(@PathVariable Long id) {
        return companyService.show(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompanyDto> add(@RequestBody @Valid CompanyForm form) {
        return companyService.create(form);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CompanyDto> edit(@PathVariable Long id, @RequestBody @Valid CompanyForm form) {
        return companyService.update(id, form);
    }
}
