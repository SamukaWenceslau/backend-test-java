package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class ParkingManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime entrance = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private LocalDateTime exit;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.ESTACIONADO;


    public ParkingManager(Address address, Vehicle vehicle) {
        this.address = address;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ParkingManager{" +
                "id=" + id +
                ", entrance=" + entrance +
                ", exit=" + exit +
                ", status=" + status +
                '}';
    }
}
