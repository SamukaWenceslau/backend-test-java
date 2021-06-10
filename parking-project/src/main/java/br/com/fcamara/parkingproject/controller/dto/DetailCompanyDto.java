package br.com.fcamara.parkingproject.controller.dto;


import br.com.fcamara.parkingproject.model.Company;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DetailCompanyDto {

    private Long id;
    private String name;
    private List<AddressDto> addresses = new ArrayList<>();

    public DetailCompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.addresses.addAll(company.getAddresses().stream().map(AddressDto::new)
                .collect(Collectors.toList()));
    }





}
