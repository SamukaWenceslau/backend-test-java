package br.com.fcamara.parkingproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "address")
    private ParkingLot parkingLot;

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
}
