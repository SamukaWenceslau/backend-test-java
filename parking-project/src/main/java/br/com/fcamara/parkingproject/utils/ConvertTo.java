package br.com.fcamara.parkingproject.utils;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.controller.dto.CompanyDto;
import br.com.fcamara.parkingproject.controller.dto.ParkingManagerDto;
import br.com.fcamara.parkingproject.controller.dto.VehicleDto;
import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.controller.form.CompanyForm;
import br.com.fcamara.parkingproject.controller.form.NewVehicleForm;
import br.com.fcamara.parkingproject.exception.ApiException;
import br.com.fcamara.parkingproject.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class ConvertTo {


    // Metodos para - Company

    public static Company company(CompanyForm form) {
        return new Company(
                form.getName(),
                form.getEmail(),
                form.getPassword(),
                form.getCnpj(),
                form.getTel()
        );
    }

    public static CompanyDto companyDto(Company company) {
        return new CompanyDto(company);
    }


    // Metodos para - Address

    public static Address address(AddressForm form, Company company) {

        Address address = new Address(
                form.getStreet(),
                form.getNeighborhood(),
                form.getNumber(),
                form.getCity(),
                form.getState(),
                form.getZip(),
                form.getName());

        address.setCompany(company);

        return address;
    }

    public static AddressDto addressDto(Address address) {
        return new AddressDto(address);
    }


    // Metodo para - ParkingLot

    public static ParkingLot parkingLot(AddressForm form, Address address) {

        ParkingLot parkingLot = new ParkingLot(form.getMotocycleSpaces(), form.getCarSpaces());
        parkingLot.setAddress(address);

        return parkingLot;
    }

    // Metodos para - Vehicle

    public static Vehicle vehicle(NewVehicleForm form) {
        return new Vehicle(
                form.getBrand(),
                form.getModel(),
                form.getColor(),
                form.getLicensePlate(),
                form.getVehicleType()
        );
    }

    public static VehicleDto vehicleDto(Vehicle vehicle) {
        return new VehicleDto(vehicle);
    }


    // Metodos para - ParkingManager

    public static ParkingManager parkingManager(Address address, Vehicle vehicle) {
        return new ParkingManager(address, vehicle);
    }

    public static ParkingManagerDto parkingManagerDto(ParkingManager parkingManager) {
        return new ParkingManagerDto(parkingManager);
    }




    // Outros

    public static UsernamePasswordAuthenticationToken
    usernamePasswordAuthenticationToken(String email, String password) {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public static ApiException apiException(String message, HttpStatus status) {
        return new ApiException(message, status);
    }
}
