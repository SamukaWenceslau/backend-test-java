package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody @Valid VehicleForm form) {

        return vehicleService.create(form);

    }

}
