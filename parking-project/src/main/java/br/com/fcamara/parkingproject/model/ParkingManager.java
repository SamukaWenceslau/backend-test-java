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
@ToString
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class ParkingManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkingLot;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime entrance = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private LocalDateTime exit = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.ESTACIONADO;




}
