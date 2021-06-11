package br.com.fcamara.parkingproject.controller.dto;


import br.com.fcamara.parkingproject.model.Company;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CompanyAddressesDto {

    private long company_id;
    private String company_name;
    private List<AddressDto> addresses = new ArrayList<>();

    public CompanyAddressesDto(Company company) {
        this.company_id = company.getId();
        this.company_name = company.getName();
        this.addresses.addAll(company.getAddresses().stream().map(AddressDto::new)
                .collect(Collectors.toList()));
    }
}
