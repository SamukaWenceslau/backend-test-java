package br.com.fcamara.parkingproject.utils;

import br.com.fcamara.parkingproject.controller.dto.ParkingManagerDto;
import br.com.fcamara.parkingproject.exception.ApiException;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import org.springframework.http.HttpStatus;

public class ConvertTo {



    // Metodos para - ParkingManager
    public static ParkingManager parkingManager(Address address, Vehicle vehicle) {
        return new ParkingManager(address, vehicle);
    }

    public static ParkingManagerDto parkingManagerDto(ParkingManager parkingManager) {
        return new ParkingManagerDto(parkingManager);
    }


    public static ApiException apiException(String message, HttpStatus status) {
        return new ApiException(message, status);
    }
}
