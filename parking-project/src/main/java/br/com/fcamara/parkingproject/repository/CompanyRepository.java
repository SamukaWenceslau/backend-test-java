package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

   Boolean existsByCnpj(String cnpj);

}
