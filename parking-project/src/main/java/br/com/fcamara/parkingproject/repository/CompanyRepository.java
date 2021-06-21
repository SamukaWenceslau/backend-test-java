package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

   Boolean existsByCnpj(String cnpj);

   Boolean existsByEmail(String email);

   Optional<Company> findByEmail(String email);

}
