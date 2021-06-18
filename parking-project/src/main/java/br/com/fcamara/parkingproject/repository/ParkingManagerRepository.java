package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.model.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingManagerRepository extends JpaRepository<ParkingManager, Long>,
        JpaSpecificationExecutor<ParkingManager> {

     Optional<ParkingManager> findByVehicleAndStatus(Vehicle vehicle, VehicleStatus status);

     @Query(value = "SELECT pm, v FROM ParkingManager pm INNER JOIN Vehicle v ON pm.vehicle = v.id")
     List<ParkingManager> findAllManagerVehicle();


}
