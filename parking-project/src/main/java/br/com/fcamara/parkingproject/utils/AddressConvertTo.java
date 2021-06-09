package br.com.fcamara.parkingproject.utils;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.Company;


public class AddressConvertTo {

    public Address convertToAddress(AddressForm form, Company company) {

        Address address = new Address(
                form.getStreet(),
                form.getNeighborhood(),
                form.getNumber(),
                form.getCity(),
                form.getState(),
                form.getZip(),
                form.getName());

        address.setCompany(company);

        return address;
    }

    public AddressDto convertToDto(Address address) {
        return new AddressDto(address);
    }
}
