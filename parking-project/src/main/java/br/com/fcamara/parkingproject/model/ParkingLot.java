package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int motocycle_spaces;
    private int car_spaces;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}

