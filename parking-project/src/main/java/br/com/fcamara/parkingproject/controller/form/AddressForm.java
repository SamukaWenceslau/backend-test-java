package br.com.fcamara.parkingproject.controller.form;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddressForm {

    private String name;

    @NotNull @NotEmpty @Size(min = 5)
    private String street;

    @NotNull @NotEmpty @Size(min = 5)
    private String neighborhood;

    @NotNull
    private Integer number;

    @NotNull @NotEmpty @Size(min = 5)
    private String city;

    @NotNull @NotEmpty @Size(min = 5)
    private String state;

    @NotNull @NotEmpty @Size(min = 5)
    private String zip;

    @NotNull
    private Long motocycleSpaces;

    @NotNull
    private Long carSpaces;




}
