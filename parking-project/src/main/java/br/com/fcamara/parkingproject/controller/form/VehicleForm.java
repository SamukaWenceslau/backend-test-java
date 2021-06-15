package br.com.fcamara.parkingproject.controller.form;


import br.com.fcamara.parkingproject.controller.annotation.EnumTypeIsValid;
import br.com.fcamara.parkingproject.model.VehicleType;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VehicleForm {

    @NotNull @NotEmpty @Size(min = 2)
    private String brand;

    @NotNull @NotEmpty @Size(min = 2)
    private String model;

    @NotNull @NotEmpty @Size(min = 2)
    private String color;

    @NotNull @NotEmpty @Size(max = 8)
    private String licensePlate;

    @EnumTypeIsValid(enumClass = VehicleType.class)
    private String vehicleType;

}
