package br.com.fcamara.parkingproject.service;

import br.com.fcamara.parkingproject.model.ParkingLot;
import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.repository.ParkingManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingManagerService {

    @Autowired
    private ParkingManagerRepository parkingManagerRepository;


    public void registerEntrance(ParkingLot parkingLot, Vehicle vehicle) {
        ParkingManager parkingVehicle = new ParkingManager(parkingLot, vehicle);
        parkingManagerRepository.save(parkingVehicle);
    }

}
