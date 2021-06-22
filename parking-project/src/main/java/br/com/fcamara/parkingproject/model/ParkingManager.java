package br.com.fcamara.parkingproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
