package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Boolean existsByLicensePlate(String licensePlate);
}
