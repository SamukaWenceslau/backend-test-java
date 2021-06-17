package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.model.*;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.ParkingManagerRepository;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class ParkingManagerService {

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private AddressRepository addressRepository;


    public ResponseEntity<?> register(VehicleForm form) {

        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(form.getLicensePlate());

        if (entranceValidate(address, vehicle)) {
            parkingManagerRepository.save(new ParkingManager(address.get(), vehicle.get()));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<?> registerNew(NewVehicleForm form) {
        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> createdVehicle = vehicleService.create(form);

        if (address.isPresent() && createdVehicle.isPresent()) {
            parkingManagerRepository.save(new ParkingManager(address.get(), createdVehicle.get()));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();
    }
    

    public ResponseEntity<?> registerExit(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isPresent()) {
            Optional<ParkingManager> parkingVechile = parkingManagerRepository
                    .findByVehicleAndStatus(vehicle.get(), VehicleStatus.ESTACIONADO);

            if(parkingVechile.isPresent()) {

                parkingVechile.get().setExit(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                parkingVechile.get().setStatus(VehicleStatus.ESTACIONOU);

                return ResponseEntity.status(HttpStatus.OK).build();
            }

        }

        return ResponseEntity.notFound().build();

    }



    private Boolean entranceValidate(Optional<Address> address, Optional<Vehicle> vehicle) {

        if(address.isPresent() && vehicle.isPresent()) {

            Optional<ParkingManager> isParking = parkingManagerRepository
                    .findByVehicleAndStatus(vehicle.get(), VehicleStatus.ESTACIONADO);

            return (!isParking.isPresent()) ? true : false;
        }

        return false;

    }


}
