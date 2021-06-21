package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.UpdateVehicleForm;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.repository.ParkingLotRepository;
import br.com.fcamara.parkingproject.repository.VehicleRepository;
import br.com.fcamara.parkingproject.utils.ConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService extends ConvertTo {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingManagerService parkingManagerService;


    public ResponseEntity<VehicleDto> show(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if(vehicle.isPresent()) {
            return ResponseEntity.ok(vehicleDto(vehicle.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public Vehicle create(NewVehicleForm form) {
            return vehicleRepository.save(vehicle(form));
    }

    public ResponseEntity<VehicleDto> update(Long id, UpdateVehicleForm form) {
        boolean existsId = vehicleRepository.existsById(id);

        if(existsId) {
            Vehicle vehicle = vehicleRepository.getById(id);

            vehicle.setBrand(form.getBrand());
            vehicle.setModel(form.getModel());
            vehicle.setColor(form.getColor());
            vehicle.setLicensePlate(form.getLicensePlate());

            return ResponseEntity.ok(vehicleDto(vehicle));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> delete(Long id) {
        boolean existsId = vehicleRepository.existsById(id);

        if(existsId) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
