package br.com.fcamara.parkingproject.controller.dto;


import br.com.fcamara.parkingproject.model.Address;
import lombok.Getter;

@Getter
public class AddressDto {

    private Long id;
    private String street;
    private String neighborhood;
    private Integer number;
    private String city;
    private String state;
    private String zip;
    private String name;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.neighborhood = address.getNeighborhood();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.state = address.getState();
        this.zip = address.getZip();
        this.name = address.getName();
    }
}
