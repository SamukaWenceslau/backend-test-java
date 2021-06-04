package br.com.fcamara.parkingproject.repository;

import br.com.fcamara.parkingproject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
