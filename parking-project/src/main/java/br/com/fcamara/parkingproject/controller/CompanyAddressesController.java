package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.CompanyAddressesDto;
import br.com.fcamara.parkingproject.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class CompanyAddressesController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Lista as informações dos endereços de uma empresa.")
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyAddressesDto> listCompanyAddresses(@PathVariable Long id) {
        return addressService.index(id);
    }
}
