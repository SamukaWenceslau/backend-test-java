package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
