package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.dto.CompanyAddressesDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.Company;
import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.CompanyRepository;
import br.com.fcamara.parkingproject.utils.AddressConvertTo;
import br.com.fcamara.parkingproject.utils.ParkingLotConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Utils
    private final AddressConvertTo addressConvertTo;
    private final ParkingLotConvertTo parkingLotConvertTo;

    public AddressService() {
        this.addressConvertTo = new AddressConvertTo();
        this.parkingLotConvertTo = new ParkingLotConvertTo();
    }

    public ResponseEntity<CompanyAddressesDto> index(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            return ResponseEntity.ok(new CompanyAddressesDto(company.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AddressDto> show(Long id) {

        Optional<Address> address = addressRepository.findById(id);

        if(address.isPresent()) {
            return ResponseEntity.ok(new AddressDto(address.get()));
        }

        return ResponseEntity.notFound().build();
    }

    // Criar -> endereço e patio
    public ResponseEntity<AddressDto> create(Long id, AddressForm form) {

        Optional<Company> company = companyRepository.findById(id);
        Boolean existsZip = addressRepository.existsByZip(form.getZip());

        if (company.isPresent() && !existsZip) {
            Address address = addressConvertTo.convertToAddress(form, company.get());

            ParkingLot parkingLot = parkingLotConvertTo.convertToParkingLot(form, address);
            parkingLotService.create(parkingLot);

            addressRepository.save(address);
            address.setParkingLot(parkingLot);

            return ResponseEntity.ok(addressConvertTo.convertToDto((address)));
        }

        return ResponseEntity.notFound().build();

    }

    // Atualizar -> endereço e patio
    public ResponseEntity<AddressDto> update(Long id, AddressForm form) {
        Boolean optional = addressRepository.existsById(id);

        if (optional) {
            Address address = addressRepository.getById(id);

            address.setName(form.getName());
            address.setStreet(form.getStreet());
            address.setNeighborhood(form.getNeighborhood());
            address.setNumber(form.getNumber());
            address.setCity(form.getCity());
            address.setState(form.getState());
            address.setZip(form.getZip());

            parkingLotService.update(form, address);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

    // Deletar -> endereço e patio
    public ResponseEntity<?> delete(Long id) {
        Boolean optional = addressRepository.existsById(id);

        if(optional) {
            addressRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


}
