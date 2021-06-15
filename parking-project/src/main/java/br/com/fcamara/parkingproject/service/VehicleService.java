package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.UpdateVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public ResponseEntity<VehicleDto> create(VehicleForm form) {

        Boolean existsLicensePlate = vehicleRepository.existsByLicensePlate(form.getLicensePlate());

        if(!existsLicensePlate) {
            Vehicle vehicle = new Vehicle(
                    form.getBrand(),
                    form.getModel(),
                    form.getColor(),
                    form.getLicensePlate(),
                    form.getVehicleType()
            );

            Vehicle save = vehicleRepository.save(vehicle);

            return ResponseEntity.ok(new VehicleDto(save));

        }

        return ResponseEntity.badRequest().build();

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
