package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.UpdateVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> showVehicle(@PathVariable Long id) {
        return vehicleService.show(id);
    }

    @PostMapping("/parkinglot/{id}")
    @Transactional
    public ResponseEntity<VehicleDto> addVehicle(@PathVariable Long id, @RequestBody @Valid VehicleForm form) {
        return vehicleService.createNewVehicle(id, form);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VehicleDto> editVehicle(@PathVariable Long id, @RequestBody @Valid UpdateVehicleForm form) {
        return vehicleService.update(id, form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeVehicle(@PathVariable Long id) {
        return vehicleService.delete(id);
    }
}
