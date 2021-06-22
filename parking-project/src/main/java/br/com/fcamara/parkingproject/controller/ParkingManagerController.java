package br.com.fcamara.parkingproject.controller;

import br.com.fcamara.parkingproject.controller.dto.ParkingManagerDto;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.controller.form.VehicleForm;
import br.com.fcamara.parkingproject.service.ParkingManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

    @Autowired
    private ParkingManagerService parkingManagerService;

    @ApiOperation(value = "Lista todos os veículos que estacionaram ou estão estacionados no endereço.")
    @GetMapping("/address/{id}")
    public ResponseEntity<?> list(@PathVariable Long id,
                                        @RequestParam(required = false) String status,
                                        @PageableDefault(sort = "entrance", direction = Sort.Direction.ASC)
                                        Pageable page) {
        if (status == null) {
            return parkingManagerService.index(id, page);
        }else{
            return parkingManagerService.index(id, page, status);
        }
    }

    @ApiOperation(value = "Registra entrada de um veículo já cadastrado.")
    @PostMapping("/register/vehicle")
    @Transactional
    public ResponseEntity<?> registerVehicleEntrance(@RequestBody VehicleForm form) {
        return parkingManagerService.register(form);
    }

    @ApiOperation(value = "Cadastra novo veículo e registra entrada, caso haja vaga.")
    @PostMapping("/register/new-vehicle")
    @Transactional
    public ResponseEntity<?> registerNewVehicleEntrance(@RequestBody NewVehicleForm form) {
        return parkingManagerService.registerNew(form);
    }

    @ApiOperation(value = "Registra a saída de um veículo.")
    @PutMapping("/register/vehicle/{id}")
    @Transactional
    public ResponseEntity<?> registerVehicleExit(@PathVariable Long id) {
        return parkingManagerService.registerExit(id);
    }

}
