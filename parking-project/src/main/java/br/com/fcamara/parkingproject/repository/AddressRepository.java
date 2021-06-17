package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.controller.dto.AddressDto;
import br.com.fcamara.parkingproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsByZip(String zip);

    Optional<Address> findByZip(String zip);
}
