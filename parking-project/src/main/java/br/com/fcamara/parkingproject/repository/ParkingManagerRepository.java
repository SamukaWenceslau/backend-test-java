package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingManagerRepository extends JpaRepository<ParkingManager, Long> {
}
