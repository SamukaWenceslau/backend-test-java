package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.ParkingManagerDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.model.*;
import br.com.fcamara.parkingproject.repository.AddressRepository;
import br.com.fcamara.parkingproject.repository.ParkingManagerRepository;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import br.com.fcamara.parkingproject.specification.SpecificationParkingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingManagerService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;

    @Autowired
    private AddressRepository addressRepository;



    public List<ParkingManagerDto> index(Long id) {
        List<ParkingManager> allParkedVehicle = parkingManagerRepository
                .findAll(Specification.where(
                        SpecificationParkingManager.address(id)
                ));

        return allParkedVehicle.stream().map(ParkingManagerDto::new).collect(Collectors.toList());
    }

    public ResponseEntity<ParkingManagerDto> register(VehicleForm form) {

        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(form.getLicensePlate());

        if (entranceValidate(address, vehicle)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(save(address.get(), vehicle.get()));
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<ParkingManagerDto> registerNew(NewVehicleForm form) {
        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> createdVehicle = vehicleService.create(form);

        if (address.isPresent() && createdVehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(save(address.get(), createdVehicle.get()));
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

    private ParkingManagerDto save(Address address, Vehicle vehicle) {
        ParkingManager save = parkingManagerRepository.save(new ParkingManager(address, vehicle));

        return new ParkingManagerDto(save);
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
