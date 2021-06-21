package br.com.fcamara.parkingproject.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateVehicleForm {

    @NotNull @NotEmpty @Size(min = 2)
    private String brand;

    @NotNull @NotEmpty @Size(min = 2)
    private String model;

    @NotNull @NotEmpty @Size(min = 2)
    private String color;

    @NotNull @NotEmpty @Size(max = 8)
    private String licensePlate;

}
