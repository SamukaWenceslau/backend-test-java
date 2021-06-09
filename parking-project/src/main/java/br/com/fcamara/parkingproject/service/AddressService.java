package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.Company;
import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.CompanyRepository;
import br.com.fcamara.parkingproject.utils.AddressConvertTo;
import br.com.fcamara.parkingproject.utils.ParkingLotConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddressService {

    // Repositories
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    // Services
    @Autowired
    ParkingLotService parkingLotService;

    private final AddressConvertTo addressConvertTo;
    private final ParkingLotConvertTo parkingLotConvertTo;

    public AddressService() {
        this.addressConvertTo = new AddressConvertTo();
        this.parkingLotConvertTo = new ParkingLotConvertTo();
    }

    public AddressDto create(AddressForm form) {

        Optional<Company> company = companyRepository.findById(1L);

        Address address = addressConvertTo.convertToAddress(form, company.get());

        ParkingLot parkingLot = parkingLotConvertTo.convertToParkingLot(form, address);

        parkingLotService.create(parkingLot);

        return new AddressDto(addressRepository.save(address));

    }


}
