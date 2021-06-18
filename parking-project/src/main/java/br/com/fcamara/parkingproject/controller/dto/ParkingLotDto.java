package br.com.fcamara.parkingproject.controller.dto;

import br.com.fcamara.parkingproject.model.ParkingLot;
import lombok.Getter;

@Getter
public class ParkingLotDto {
    private Long id;
    private Long motocycle_spaces;
    private Long car_spaces;

    public ParkingLotDto(ParkingLot parkingLot) {
        this.id = parkingLot.getId();
        this.motocycle_spaces = parkingLot.getMotocycleSpaces();
        this.car_spaces = parkingLot.getCarSpaces();
    }
}
