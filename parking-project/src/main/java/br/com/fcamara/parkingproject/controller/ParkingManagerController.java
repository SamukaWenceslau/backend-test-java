package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.service.ParkingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

    @Autowired
    private ParkingManagerService parkingManagerService;

    @PostMapping("/register/vehicle")
    @Transactional
    public ResponseEntity<?> registerVehicleEntrance(@RequestBody VehicleForm form) {
        return parkingManagerService.register(form);
    }

    @PostMapping("/register/new-vehicle")
    @Transactional
    public ResponseEntity<?> registerNewVehicleEntrance(@RequestBody NewVehicleForm form) {
        return parkingManagerService.registerNew(form);
    }

}
