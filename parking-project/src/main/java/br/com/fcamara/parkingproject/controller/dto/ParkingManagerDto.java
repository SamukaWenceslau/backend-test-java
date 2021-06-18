package br.com.fcamara.parkingproject.controller.dto;

import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.VehicleStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ParkingManagerDto {

    private Long id;
    private LocalDateTime entrance;
    private LocalDateTime exit;
    private VehicleStatus status;
    private VehicleDto vehicle;

    public ParkingManagerDto(ParkingManager parkingManager) {
        this.id = parkingManager.getId();
        this.entrance = parkingManager.getEntrance();
        this.exit = parkingManager.getExit();
        this.status = parkingManager.getStatus();
        this.vehicle = new VehicleDto(parkingManager.getVehicle());
    }
}
