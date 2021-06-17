package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.UpdateVehicleForm;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.repository.ParkingLotRepository;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingManagerService parkingManagerService;


    public ResponseEntity<VehicleDto> show(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if(vehicle.isPresent()) {
            return ResponseEntity.ok(new VehicleDto(vehicle.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public Optional<Vehicle> create(NewVehicleForm form) {
        boolean existsVehicle = vehicleRepository.existsByLicensePlate(form.getLicensePlate());
        Optional<Vehicle> createdVehicle = Optional.empty();

        if (!existsVehicle) {
            Vehicle vehicle = new Vehicle(
                    form.getBrand(),
                    form.getModel(),
                    form.getColor(),
                    form.getLicensePlate(),
                    form.getVehicleType());

            vehicleRepository.save(vehicle);

            createdVehicle = Optional.of(vehicle);
        }

        return createdVehicle;

    }

    public ResponseEntity<VehicleDto> update(Long id, UpdateVehicleForm form) {
        boolean existsID = vehicleRepository.existsById(id);

        if(existsID) {
            Vehicle vehicle = vehicleRepository.getById(id);

            vehicle.setBrand(form.getBrand());
            vehicle.setModel(form.getModel());
            vehicle.setColor(form.getColor());
            vehicle.setLicensePlate(form.getLicensePlate());

            return ResponseEntity.ok(new VehicleDto(vehicle));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> delete(Long id) {
        boolean existsID = vehicleRepository.existsById(id);

        if(existsID) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
