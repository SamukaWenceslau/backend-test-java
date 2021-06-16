package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.service.ParkingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

    @Autowired
    private ParkingManagerService parkingManagerService;

}
