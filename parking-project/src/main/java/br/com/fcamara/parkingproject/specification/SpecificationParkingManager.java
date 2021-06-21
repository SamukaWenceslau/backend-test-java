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

    public static Specification<ParkingManager> vehicleStatus(String vehicleStatus) {
        return new Specification<ParkingManager>() {
            @Override
            public Predicate toPredicate(Root<ParkingManager> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Predicate statusIsParked = criteriaBuilder.equal(root.get("status"), VehicleStatus.ESTACIONADO);
                Predicate statusWasParked = criteriaBuilder.equal(root.get("status"), VehicleStatus.ESTACIONOU);

                String upperVehicleStatus = vehicleStatus.toUpperCase();
                if(upperVehicleStatus.equals("ESTACIONADO") || upperVehicleStatus.equals("ESTACIONOU")) {
                    VehicleStatus status = VehicleStatus.valueOf(upperVehicleStatus);
                    return criteriaBuilder.equal(root.get("status"), status);
                }else {
                    return criteriaBuilder.or(statusIsParked, statusWasParked);
                }

            }
        };
    }

    public static Specification<ParkingManager> vehicleType(VehicleType type) {

        return new Specification<ParkingManager>() {
            @Override
            public Predicate toPredicate(Root<ParkingManager> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<ParkingManager, Vehicle> join = root.join("vehicle");
                return  criteriaBuilder.equal(join.get("vehicle"), type);
            }
        };
    }

}
