package br.com.fcamara.parkingproject.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterVehicleForm {

    @NotNull @NotEmpty @Size(max = 8)
    private String LicensePlate;


}
