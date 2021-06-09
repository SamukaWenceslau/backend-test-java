package br.com.fcamara.parkingproject.repository;


import br.com.fcamara.parkingproject.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
