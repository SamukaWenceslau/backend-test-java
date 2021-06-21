package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.model.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingManagerRepository extends JpaRepository<ParkingManager, Long>,
        JpaSpecificationExecutor<ParkingManager> {

     Optional<ParkingManager> findByVehicleAndStatus(Vehicle vehicle, VehicleStatus status);

}
