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
    private int number;
    private String city;
    private String state;
    private String zip;
    private String name;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private ParkingLot parkingLot;


}
