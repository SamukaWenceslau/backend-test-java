package br.com.fcamara.parkingproject.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String color;

    @Column(name = "license_Plate")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicle;

    @OneToOne(mappedBy = "vehicle")
    private ParkingManager parkingManager;

    public Vehicle(String brand, String model, String color, String licensePlate, String vehicle) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.vehicle = converterStringToVehicleType(vehicle);
    }


    private VehicleType converterStringToVehicleType(String value) {
        VehicleType vehicleType = VehicleType.valueOf(value.toUpperCase());
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
