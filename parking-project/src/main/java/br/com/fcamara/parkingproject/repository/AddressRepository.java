package br.com.fcamara.parkingproject.repository;


import br.com.fcamara.parkingproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsByZip(String zip);

    Optional<Address> findByZip(String zip);
}
