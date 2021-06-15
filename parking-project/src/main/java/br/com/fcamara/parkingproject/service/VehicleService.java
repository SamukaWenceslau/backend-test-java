package br.com.fcamara.parkingproject.service;

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


    public ResponseEntity<?> create(VehicleForm form) {

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

            return ResponseEntity.ok(save);

        }

        return ResponseEntity.badRequest().build();

    }

}
