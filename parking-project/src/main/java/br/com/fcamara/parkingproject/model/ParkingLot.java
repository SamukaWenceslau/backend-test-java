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

    @Column(name = "motocycle_spaces")
    private Integer motocycleSpaces;

    @Column(name = "car_spaces")
    private Integer carSpaces;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "address_id")
    private Address address;

    public ParkingLot(int motocycleSpaces, int carSpaces) {
        this.motocycleSpaces = motocycleSpaces;
        this.carSpaces = carSpaces;
    }
}

