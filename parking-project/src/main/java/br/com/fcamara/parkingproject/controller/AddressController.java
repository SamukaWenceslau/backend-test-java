package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Lista os dados de um endereço.")
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> showAddress(@PathVariable Long id) {
        return addressService.show(id);
    }

    @ApiOperation(value = "Cadastra um endereço de uma empresa.")
    @PostMapping("/company/{id}")
    @Transactional
    public ResponseEntity<AddressDto> addAddress(@PathVariable Long id, @RequestBody @Valid AddressForm form) {
        return addressService.create(id, form);
    }

    @ApiOperation(value = "Atualiza dados de um endereço")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editAddress(@PathVariable Long id, @RequestBody @Valid AddressForm form) {
        return addressService.update(id, form);
    }

    @ApiOperation(value = "Remove um endereço e tudo que estiver associado.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeAddress(@PathVariable Long id) {
        return addressService.delete(id);
    }

}
