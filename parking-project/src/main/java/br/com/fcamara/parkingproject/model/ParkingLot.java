package br.com.fcamara.parkingproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motocycle_spaces")
    private Long motocycleSpaces;

    @Column(name = "car_spaces")
    private Long carSpaces;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "address_id")
    private Address address;

    public ParkingLot(Long motocycleSpaces, Long carSpaces) {
        this.motocycleSpaces = motocycleSpaces;
        this.carSpaces = carSpaces;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", motocycleSpaces=" + motocycleSpaces +
                ", carSpaces=" + carSpaces +
                '}';
    }
}

