package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.ParkingManagerDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.exception.ApiException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingManagerService {

    private final HttpStatus created = HttpStatus.CREATED;
    private final HttpStatus notFound = HttpStatus.NOT_FOUND;
    private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;

    @Autowired
    private AddressRepository addressRepository;



    public List<ParkingManagerDto> index(Long id) {
        boolean existsAddress = addressRepository.existsById(id);

        if (existsAddress) {
            List<ParkingManager> allParkedVehicle = parkingManagerRepository
                    .findAll(Specification.where(
                            SpecificationParkingManager.address(id)
                    ));

            return allParkedVehicle.stream().map(ParkingManagerDto::new).collect(Collectors.toList());
        }

        return null;

    }

    public ResponseEntity<Object> register(VehicleForm form) {

        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(form.getLicensePlate().toUpperCase());

        if (address.isPresent() && vehicle.isPresent()) {
            if (entranceValidate(address.get(), vehicle.get())) {

                return ResponseEntity.status(created).body(save(address.get(), vehicle.get()));

            }
            else {

            }
        }

        return ResponseEntity.status(notFound)
                .body(new ApiException("Endereço ou veículo invalido.", notFound));

    }

    public ResponseEntity<Object> registerNew(NewVehicleForm form) {
        Optional<Address> address = addressRepository.findByZip(form.getZip());
        Boolean existsVehicle = vehicleRepository.existsByLicensePlate(form.getLicensePlate());

        if (address.isPresent() && !existsVehicle) {

            Vehicle vehicle = vehicleService.create(form);

            if (hasSpace(address.get(), vehicle)){

                return ResponseEntity.status(created)
                        .body(save(address.get(), vehicle));
            }else{

                return ResponseEntity.status(created)
                        .body(new ApiException("Veículo cadastrado, porém não foi possivel " +
                                "estaciona-lo. Pois não há espaço.", created));

            }
        }

        return ResponseEntity.status(badRequest)
                .body(new ApiException("O endereço não foi encontrado. " +
                        "Ou o veículo já está cadastrado.", badRequest));
    }

    public ResponseEntity<Object> registerExit(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isPresent()) {
            Optional<ParkingManager> parkingVechile = parkingManagerRepository
                    .findByVehicleAndStatus(vehicle.get(), VehicleStatus.ESTACIONADO);

            if(parkingVechile.isPresent()) {

                parkingVechile.get().setExit(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                parkingVechile.get().setStatus(VehicleStatus.ESTACIONOU);

                return ResponseEntity.status(HttpStatus.OK).build();

            }else {
                return ResponseEntity.status(notFound)
                        .body(new ApiException("Veículo não está estacionado.", notFound));
            }

        }

            return ResponseEntity.status(notFound)
                    .body(new ApiException("Veículo não encontrado.", notFound));


    }



    private ParkingManagerDto save(Address address, Vehicle vehicle) {
        ParkingManager save = parkingManagerRepository.save(new ParkingManager(address, vehicle));

        return new ParkingManagerDto(save);
    }


    private Boolean entranceValidate(Address address, Vehicle vehicle) {

        if(!isParked(vehicle) && hasSpace(address, vehicle)) {
            return true;
        }

        return false;
    }

    private boolean isParked(Vehicle vehicle) {
        Optional<ParkingManager> isParked = parkingManagerRepository
                .findByVehicleAndStatus(vehicle, VehicleStatus.ESTACIONADO);

        return (isParked.isPresent()) ? true : false;

    }

    private boolean hasSpace(Address address, Vehicle vehicle) {
//        List<ParkingManager> allParkedVehicle = parkingManagerRepository
//                .findAll(Specification.where(SpecificationParkingManager.address(address.getId()))
//                        .and(SpecificationParkingManager.spaceLimit(vehicle.getVehicle())));

        List<ParkingManager> allManagerVehicle = parkingManagerRepository.findAllManagerVehicle();

        allManagerVehicle
                .stream()
                .filter(e -> e.getVehicle().equals(vehicle.getVehicle()) && e.getStatus().equals(VehicleStatus.ESTACIONADO) &&
                        e.getAddress().getId().equals(address.getId())).count();

        Long carSpaces = address.getParkingLot().getCarSpaces();
        Long motocycleSpaces = address.getParkingLot().getMotocycleSpaces();

        if (vehicle.getVehicle().equals(VehicleType.CARRO)) {
            return (allManagerVehicle.size() == carSpaces.intValue()) ? false : true;
        }else {
            return (allManagerVehicle.size() == motocycleSpaces.intValue()) ? false : true;
        }
    }



}
