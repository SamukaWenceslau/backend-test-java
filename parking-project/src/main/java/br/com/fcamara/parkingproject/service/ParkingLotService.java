package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ParkingLot create(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public void update(AddressForm form, Address address) {

        ParkingLot parkingLot = parkingLotRepository.getById(address.getId());

        parkingLot.setCarSpaces(form.getCarSpaces());
        parkingLot.setMotocycleSpaces(form.getMotocycleSpaces());

    }
}
