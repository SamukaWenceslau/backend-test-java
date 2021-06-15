package br.com.fcamara.parkingproject.controller.dto;

import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.model.VehicleType;
import lombok.Getter;

@Getter
public class VehicleDto {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String licensePlate;
    private VehicleType vehicle;

    public VehicleDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.color = vehicle.getColor();
        this.licensePlate = vehicle.getLicensePlate();
        this.vehicle = vehicle.getVehicle();
    }
}
