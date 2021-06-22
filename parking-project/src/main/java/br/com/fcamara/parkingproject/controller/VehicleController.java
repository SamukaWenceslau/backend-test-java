package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.UpdateVehicleForm;
import br.com.fcamara.parkingproject.service.VehicleService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Lista dados de um veículo.")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> showVehicle(@PathVariable Long id) {
        return vehicleService.show(id);
    }

    @ApiOperation(value = "Atualiza dados de um veículo.")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VehicleDto> editVehicle(@PathVariable Long id, @RequestBody @Valid UpdateVehicleForm form) {
        return vehicleService.update(id, form);
    }

    @ApiOperation(value = "Remove um veículo.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeVehicle(@PathVariable Long id) {
        return vehicleService.delete(id);
    }
}
