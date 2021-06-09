package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.Company;
import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddressService {

    //Repositories

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    //Services

    @Autowired
    ParkingLotService parkingLotService;


    public AddressDto create(AddressForm form) {

        Optional<Company> company = companyRepository.findById(1L);

        Address address = new Address(
                form.getStreet(),
                form.getNeighborhood(),
                form.getNumber(),
                form.getCity(),
                form.getState(),
                form.getZip(),
                form.getName());

        address.setCompany(company.get());

        ParkingLot parkingLot = new ParkingLot(form.getMotocycleSpaces(), form.getCarSpaces());
        parkingLot.setAddress(address);

        Address save = addressRepository.save(address);
        parkingLotService.create(parkingLot);

        return new AddressDto(save);

    }


}
