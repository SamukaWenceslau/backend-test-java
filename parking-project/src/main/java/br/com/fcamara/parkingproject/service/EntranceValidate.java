package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.model.*;
import br.com.fcamara.parkingproject.repository.ParkingManagerRepository;
import br.com.fcamara.parkingproject.specification.SpecificationParkingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntranceValidate {

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;

    public Boolean canPark(Address address, Vehicle vehicle) {

        if(!isParked(vehicle) && hasSpace(address, vehicle)) {
            return true;
        }

        return false;
    }

    public Boolean isParked(Vehicle vehicle) {
        Optional<ParkingManager> isParked = parkingManagerRepository
                .findByVehicleAndStatus(vehicle, VehicleStatus.ESTACIONADO);

        return (isParked.isPresent()) ? true : false;

    }

    public Boolean hasSpace(Address address, Vehicle vehicle) {

        List<ParkingManager> all = parkingManagerRepository
                .findAll(Specification.where(
                        SpecificationParkingManager.address(address.getId())
                                .and(SpecificationParkingManager.vehicleStatus("ESTACIONADO"))
                                .and(SpecificationParkingManager.vehicleType(vehicle.getVehicle()))
                ));

        Long carSpaces = address.getParkingLot().getCarSpaces();
        Long motocycleSpaces = address.getParkingLot().getMotocycleSpaces();

        if (vehicle.getVehicle().equals(VehicleType.CARRO)) {
            return (all.size() == carSpaces.intValue()) ? false : true;
        }else {
            return (all.size() == motocycleSpaces.intValue()) ? false : true;
        }
    }
}
