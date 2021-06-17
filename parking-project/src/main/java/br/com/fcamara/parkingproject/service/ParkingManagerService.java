package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.ParkingManagerRepository;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingManagerService {

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AddressRepository addressRepository;


    public ResponseEntity<?> register(VehicleForm form) {

        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(form.getLicensePlate());

        if (address.isPresent() && vehicle.isPresent()) {
            ParkingManager vehicleEntrance = new ParkingManager(address.get(), vehicle.get());
            parkingManagerRepository.save(vehicleEntrance);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.notFound().build();

    }



}
