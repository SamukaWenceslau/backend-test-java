package br.com.fcamara.parkingproject.controller.form;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VehicleForm {

    @NotNull @NotEmpty @Size(min = 7, max = 8)
    private String licensePlate;

    @NotNull @NotEmpty @Size(min = 8, max = 9)
    private String zip;



}
