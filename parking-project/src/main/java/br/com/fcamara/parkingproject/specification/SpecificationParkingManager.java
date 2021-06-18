package br.com.fcamara.parkingproject.specification;

import br.com.fcamara.parkingproject.model.ParkingManager;
import org.springframework.data.jpa.domain.Specification;



public class SpecificationParkingManager {

    public static Specification<ParkingManager> address(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("address"), id);
    }

}
