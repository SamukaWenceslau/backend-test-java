package br.com.fcamara.parkingproject.controller;


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

    @PostMapping
    @Transactional
    public ResponseEntity<Company> add(@RequestBody @Valid CompanyForm form) {
        Company company = companyService.create(form);

        if(company != null) {
            return ResponseEntity.ok(company);
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Company> edit
            (@PathVariable long id, @RequestBody @Valid CompanyForm form) {

        Company company = companyService.update(id, form);

        if(company != null) {
            return ResponseEntity.ok(company);
        }

        return ResponseEntity.badRequest().build();
    }
}
