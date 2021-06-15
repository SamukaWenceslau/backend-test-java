package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;

import br.com.fcamara.parkingproject.service.AddressService;
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

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> showAddress(@PathVariable Long id) {
        return addressService.show(id);
    }

    @PostMapping("/company/{id}")
    @Transactional
    public ResponseEntity<AddressDto> addAddress(@PathVariable Long id, @RequestBody @Valid AddressForm form) {
        return addressService.create(id, form);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AddressDto> editAddress(@PathVariable Long id, @RequestBody @Valid AddressForm form) {
        return addressService.update(id, form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeAddress(@PathVariable Long id) {
        return addressService.delete(id);
    }

}
