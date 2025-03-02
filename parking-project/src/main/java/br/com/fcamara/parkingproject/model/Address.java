package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String neighborhood;
    private Integer number;
    private String city;
    private String state;
    private String zip;
    private String name;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToOne(mappedBy = "address", orphanRemoval = true)
    private ParkingLot parkingLot;

    @OneToOne
    private ParkingManager parkingManager;

    public Address(String street, String neighborhood, Integer number, String city,
                   String state, String zip, String name) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
