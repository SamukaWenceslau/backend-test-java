package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.service.CompanyService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Lista os dados de uma empresa.")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> showCompany(@PathVariable Long id) {
        return companyService.show(id);
    }

    @ApiOperation(value = "Cadastra uma empresa.")
    @PostMapping
    @Transactional
    public ResponseEntity<CompanyDto> addCompany(@RequestBody @Valid CompanyForm form) {
        return companyService.create(form);
    }

    @ApiOperation(value = "Atualiza dados de uma empresa.")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CompanyDto> editCompany(@PathVariable Long id, @RequestBody @Valid CompanyForm form) {
        return companyService.update(id, form);
    }
}
