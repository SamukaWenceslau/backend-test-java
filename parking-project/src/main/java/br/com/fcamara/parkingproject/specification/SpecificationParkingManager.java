package br.com.fcamara.parkingproject.specification;

import br.com.fcamara.parkingproject.model.ParkingManager;
import br.com.fcamara.parkingproject.model.Vehicle;
import br.com.fcamara.parkingproject.model.VehicleStatus;
import br.com.fcamara.parkingproject.model.VehicleType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


public class SpecificationParkingManager {

    public static Specification<ParkingManager> address(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("address"), id);
    }

    public static Specification<ParkingManager> spaceLimit(VehicleType vehicleType) {
        return new Specification<ParkingManager>() {
            @Override
            public Predicate toPredicate(Root<ParkingManager> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Join<ParkingManager, Vehicle> parkingManagerJoinVehicle = root.join("vehicle");
                Predicate status = criteriaBuilder.equal(parkingManagerJoinVehicle.get("status"), VehicleStatus.ESTACIONADO);
                Predicate parkedVehicle = criteriaBuilder.equal(parkingManagerJoinVehicle.get("vehicle"), vehicleType);

                return criteriaBuilder.and(parkedVehicle, status);
            }
        };
    }

}
